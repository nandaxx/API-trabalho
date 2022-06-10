package com.residencia.commerce.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class Arquivo2Service {

	@Value("${pasta.arquivos.imagem}")
	private String diretorioArquivos;

	private java.nio.file.Path localFinalArquivo;

	public void criarArquivo(String fileName, MultipartFile file) throws IOException {
		try {
			if (fileName.contains("..")) {
				throw new Exception("Nome do arquivo invalido");
			}
			this.localFinalArquivo = Paths.get(diretorioArquivos).toAbsolutePath().normalize();
			Path destinoLocation = localFinalArquivo.resolve(fileName);
			Files.copy(file.getInputStream(), destinoLocation, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception ex) {
			throw new IOException("Não foi possivel mover o arquivo " + ex.getStackTrace());
		}
	}
}
