CREATE  TABLE IF NOT EXISTS "Group" (
                                        `id` integer constraint table_name_pk
                                            primary key autoincrement,
                                        `number` INT NOT NULL ,
                                        `department` VARCHAR(45) NULL);

CREATE  TABLE IF NOT EXISTS Student (
                                        `id` integer NOT NULL constraint table_name_pk
                                            primary key autoincrement,
                                        `name` VARCHAR(45) NULL ,
                                        `surname` VARCHAR(45) NULL ,
                                        `enrolment_date` DATE NULL ,
                                        `group_id` INT);