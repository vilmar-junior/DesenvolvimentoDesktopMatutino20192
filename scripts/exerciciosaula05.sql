CREATE SCHEMA EXEMPLOS;

CREATE TABLE `exemplos`.`telefone` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `codigoPais` VARCHAR(2) NOT NULL,
  `ddd` VARCHAR(2) NOT NULL,
  `numero` VARCHAR(12) NOT NULL,
  `tipoLinha` VARCHAR(255) NOT NULL,
  `ativo` INT(1) NOT NULL,
  PRIMARY KEY (`id`));
  
  CREATE TABLE `exemplos`.`endereco` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cep` VARCHAR(8) NOT NULL,
  `estado` VARCHAR(255) NOT NULL,
  `cidade` VARCHAR(255) NOT NULL,
  `bairro` VARCHAR(255) NOT NULL,
  `numero` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`));
  
ALTER TABLE `exemplos`.`endereco` 
ADD COLUMN `rua` VARCHAR(255) NOT NULL AFTER `numero`;

  
  CREATE TABLE `exemplos`.`cliente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NOT NULL,
  `sobrenome` VARCHAR(255) NOT NULL,
  `cpf` VARCHAR(11) NOT NULL,
  `idEndereco` INT NOT NULL,
  PRIMARY KEY (`id`));

ALTER TABLE `exemplos`.`cliente` 
ADD INDEX `id_idx` (`idEndereco` ASC);
ALTER TABLE `exemplos`.`cliente` 
ADD CONSTRAINT `id`
  FOREIGN KEY (`idEndereco`)
  REFERENCES `exemplos`.`endereco` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `exemplos`.`telefone` 
ADD COLUMN `idCliente` INT NULL AFTER `ativo`;


  