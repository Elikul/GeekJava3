use somedates;

/*вставляем данные времени в таблицу*/
INSERT INTO Dates(DateStart,DateEnd) VALUES(120000,140000);
INSERT INTO Dates(DateStart,DateEnd) VALUES(130000,170000);
INSERT INTO Dates(DateStart,DateEnd) VALUES(90000,100000);
INSERT INTO Dates(DateStart,DateEnd) VALUES(180000,220000);
INSERT INTO Dates(DateStart,DateEnd) VALUES(180000,200000);
INSERT INTO Dates(DateStart,DateEnd) VALUES(70000,100000);

SELECT * FROM Dates;

/*запрос выводит все диапазоны времени,которые входят в диапозон времение с 12:00:00 до 17:30:00*/
SELECT DateStart, DateEnd FROM Dates where DateStart >= 120000 AND DateEnd <= 173000;

/*хранимая процедура, у который на вхлд подаются два параметра - начало временного диапозона и конец временного
диапозона и запрос выводит все диапазоны времени,которые входят в диапозон времение заданного параметрами*/
DELIMITER $$
CREATE PROCEDURE CheckDiaposon (startD TIME, endD TIME)
BEGIN
    SELECT DateStart, DateEnd FROM Dates where DateStart >= startD AND DateEnd <= endD;
END $$

/*вызовы процедуры*/
call CheckDiaposon(120000,173000);
call CheckDiaposon(060000,070000);