CREATE TABLE quantity_measurement_entity (

 id INT AUTO_INCREMENT PRIMARY KEY,
 operation VARCHAR(50),
 operand1 VARCHAR(100),
 operand2 VARCHAR(100),
 result VARCHAR(100),
 error_message VARCHAR(255)

);