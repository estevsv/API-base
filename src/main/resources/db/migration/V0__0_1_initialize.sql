--
-- Tabela `filme`
--
CREATE TABLE `filme` (
  `id` bigint NOT NULL,
  `atores` varchar(255) DEFAULT NULL,
  `diretor` varchar(255) DEFAULT NULL,
  `genero` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

--
-- Tabela `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence`(
    `next_val` bigint DEFAULT NULL
); 

--
-- Tabela `usuario`
--

CREATE TABLE `usuario` (
  `id` bigint NOT NULL,
  `administrador` bit(1) NOT NULL,
  `ativo` bit(1) NOT NULL,
  `login` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
); 

--
-- Tabela `voto`
--

CREATE TABLE `voto` (
  `id` bigint NOT NULL,
  `id_filme` bigint DEFAULT NULL,
  `id_usuario` bigint DEFAULT NULL,
  `voto_filme` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
); 
