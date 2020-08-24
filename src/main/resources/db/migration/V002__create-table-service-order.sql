CREATE TABLE service_order (
	id BIGINT NOT NULL AUTO_INCREMENT,
	client_id BIGINT NOT NULL,
	descrip TEXT NOT NULL,
	preco DECIMAL(10,2) NOT NULL,
	status VARCHAR(20) NOT NULL,
	date_open DATETIME NOT NULL,
	date_close DATETIME,
	
	PRIMARY KEY (id)
);

ALTER TABLE service_order ADD CONSTRAINT fk_service_order_client
FOREIGN KEY (client_id) REFERENCES client(id);