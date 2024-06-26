package ru.vtb.asaf.service;

import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class FileService {

	public void createFile(int countGenerate) throws IOException {
		Date dateTime = Date.from(Instant.now());
		String fileName = String.format("random_uuid_%s.csv", dateTime.toString().replaceAll("[ :]", ""));

		FileOutputStream fileOut = new FileOutputStream(fileName, true);

		int count = 0;
		try {
			while (count < countGenerate) {

				String row = UUID.randomUUID() + ";\n";
				fileOut.write(row.getBytes());

				count++;
				if (count % 1000 == 0) {
					fileOut.close();
					System.out.println("Обработано записей: " + count);
					fileOut = new FileOutputStream(fileName, true);
				}
			}

			fileOut.close();

			System.out.println("Всего обработано записей: " + count);
		} catch (RuntimeException e) {
			System.out.println("Произошла какая-то проблема при записи данных в файл");
			fileOut.close();
			Path fileDeletePath = Paths.get(fileName);
			Files.delete(fileDeletePath);
			System.out.println("Файл был удалён");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
