INSERT INTO AUTHORS VALUES ("Paul", "Aldabagh", 26-04-2000, ["How to get a job", "How to be the best engineer"])
INSERT INTO AUTHORS VALUES ("Napoleon", "Hill", 10-03-1920, "Think and grow rich")
INSERT INTO AUTHORS VALUES ("Tony", "Robbins", 13-05-1973, "No excuses")

INSERT INTO BOOKS VALUES ("How to get a job", ["Paul", Napoleon], "NONFICTION")
INSERT INTO BOOKS VALUES ("How to be the best engineer", "Paul", "NONFICTION")
INSERT INTO BOOKS VALUES ("Think and grow rich", "Napoleon", "FICTION")
INSERT INTO BOOKS VALUES ("No excuses", "Tony", "NONFICTION")

INSERT INTO AUTHOR_BOOKS VALUES (1, 1)
INSERT INTO AUTHOR_BOOKS VALUES (2, 1)
INSERT INTO AUTHOR_BOOKS VALUES (2, 3)
INSERT INTO AUTHOR_BOOKS VALUES (3, 4)