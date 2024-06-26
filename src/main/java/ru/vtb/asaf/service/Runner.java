package ru.vtb.asaf.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {

	private final FileService fileService;

	@Override
	public void run(String... args) throws Exception {

		Scanner console = new Scanner(System.in);

		System.out.println("\n *** Генератор UUID *** \n");
		System.out.print("Введи количество генерируемых UUID: ");
		System.out.print("\nВвод: ");
		try {
			int entryStr = Integer.parseInt(console.nextLine());
			fileService.createFile(entryStr);
		} catch (NumberFormatException e) {
			System.out.print("Введено не числовое значение");
		}
	}
}
