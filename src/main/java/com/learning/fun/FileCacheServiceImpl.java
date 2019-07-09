package com.learning.fun;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Data

@Component
public class FileCacheServiceImpl implements CacheService {

	private File persistenceDirectory;
	

	@Value("{persistenceDirectory}")
	private String persistenceDirectoryString;

	private FileCacheServiceImpl() {
		
	}

	@Bean(name="fileCacheService")
	public CacheService createFileCacheBean() {
		FileCacheServiceImpl fileCacheService=new FileCacheServiceImpl();
		fileCacheService.setPersistenceDirectory(new File(persistenceDirectoryString));		
		
		return fileCacheService;
	}

	@Override
	public Object get(String key) {

		File persistenceFile = pathToFile(key);
		if (!persistenceFile.exists())
			return null;

		try (FileInputStream fileInputStream = new FileInputStream(persistenceFile);) {
			return readPersisted(key, fileInputStream);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@Override
	public void put(String key, Object value) {
		persistValue(key, value);
	}

	private Object readPersisted(String key, InputStream inputStream) throws IOException {
		try {
			return new ObjectInputStream(inputStream).readObject();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(String.format("Serialized version assigned by %s was invalid", key), e);
		}
	}

	private List<String> directoryFor(String key) {
		return Arrays.asList(key);
	}

	private void persistValue(String key, Object value) {

		File persistenceFile = pathToFile(key);
		persistenceFile.getParentFile().mkdirs();

		try (FileOutputStream fileOutputStream = new FileOutputStream(persistenceFile)) {

			persist(key, value, fileOutputStream);

		} catch (Exception ex) {

		}
	}

	protected void persist(String key, Object value, OutputStream outputStream) throws IOException {
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
		objectOutputStream.writeObject(value);
		objectOutputStream.flush();
	}

	private File pathToFile(String key) {
		List<String> pathSegments = directoryFor(key);
		File persistenceFile = persistenceDirectory;
		for (String pathSegment : pathSegments) {
			persistenceFile = new File(persistenceFile, pathSegment);
		}
		if (persistenceDirectory.equals(persistenceFile) || persistenceFile.isDirectory()) {
			throw new IllegalArgumentException();
		}
		return persistenceFile;
	}

}
