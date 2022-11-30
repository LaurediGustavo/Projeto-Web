DROP DATABASE IF EXISTS `controleDeGastos`;
CREATE DATABASE IF NOT EXISTS `controleDeGastos` CHARSET utf8;

USE `controleDeGastos`;

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
	`id` int NOT NULL AUTO_INCREMENT,
	`nome` varchar(100) NOT NULL,
	`sobreNome` varchar(100) NOT NULL,
    `email` varchar(100) NOT NULL,
    `foto` varchar(100) NULL,
    `senha` varchar(100) NULL,
	PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `gasto`;
CREATE TABLE `gasto` (
	`id` int NOT NULL AUTO_INCREMENT,
	`valor` numeric(15, 2) NOT NULL,
	`dataDoGasto` date NOT NULL,
    `descricao` varchar(100) NOT NULL,
    `idUsuario` int NULL,
	PRIMARY KEY (`id`),
	FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`id`)
);

DROP TABLE IF EXISTS `ganho`;
CREATE TABLE `ganho` (
	`id` int NOT NULL AUTO_INCREMENT,
	`valor` numeric(15, 2) NOT NULL,
	`dataDoGanho` date NOT NULL,
    `descricao` varchar(100) NOT NULL,
    `idUsuario` int NULL,
	PRIMARY KEY (`id`),
	FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`id`)
);