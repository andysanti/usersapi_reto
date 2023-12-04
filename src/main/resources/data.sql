DROP TABLE IF EXISTS telefono;
DROP TABLE IF EXISTS usuario;

CREATE TABLE usuario (
        id INT AUTO_INCREMENT  PRIMARY KEY,
        name VARCHAR(250) NOT NULL,
        email VARCHAR(250) NOT NULL,
        password VARCHAR(250) NOT NULL,
        token VARCHAR(250)  NULL,
        active BIT ,
        created TIMESTAMP ,
        modified  TIMESTAMP ,
        last_login TIMESTAMP
);
CREATE TABLE telefono (
                         idtelefono INT AUTO_INCREMENT  PRIMARY KEY,
                         numero VARCHAR(250) NOT NULL,
                         countrycode VARCHAR(250) NOT NULL,
                         citycode VARCHAR(250) NOT NULL,
                         id INT NOT NULL,
                         CONSTRAINT `fk1` FOREIGN KEY (`id`) REFERENCES `usuario` (`id`)
);

INSERT INTO usuario (name, email, password,token,active,created,modified,
                     last_login) VALUES
('andy', 'andysanti23@dominio.cl', 'Abc12','123456abscdef',1,CURRENT_TIMESTAMP
,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);