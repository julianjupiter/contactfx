CREATE DATABASE contactfx;
USE contactfx;
CREATE TABLE IF NOT EXISTS contact (
	id BIGINT NOT NULL,
	first_name VARCHAR(255) NOT NULL,
	last_name VARCHAR(255) NOT NULL,
	mobile_number VARCHAR(255) NOT NULL,
	telephone_number VARCHAR(255) NULL,
	email_address VARCHAR(255) NULL,	
	address VARCHAR(255) NOT NULL,
	barangay VARCHAR(255) NOT NULL,
	city_or_municipality VARCHAR(255) NOT NULL,
	province VARCHAR(255) NOT NULL,
	zip_code VARCHAR(5) NOT NULL,
	created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_at DATETIME NULL ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (id)
);