-- V2: Migration para adicionar a coluna de ranking na tabela de cadastros

ALTER TABLE IF EXISTS tb_cadastro
    ADD COLUMN IF NOT EXISTS imgUrl VARCHAR(255);