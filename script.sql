-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema conference
-- -----------------------------------------------------
-- Конференция. Администратор создает конференцию, ее секции. Участник регистрируется и подает заявку в одну или более секций. Администратор проверяет заявку на соответствие тематике секции и потверждает/отклоняет заявку. Участник может снять заявку, задать вопрос Администратору.

-- -----------------------------------------------------
-- Schema conference
--
-- Конференция. Администратор создает конференцию, ее секции. Участник регистрируется и подает заявку в одну или более секций. Администратор проверяет заявку на соответствие тематике секции и потверждает/отклоняет заявку. Участник может снять заявку, задать вопрос Администратору.
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `conference` DEFAULT CHARACTER SET utf8 ;
USE `conference` ;

-- -----------------------------------------------------
-- Table `conference`.`conference`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `conference`.`conference` (
  `id_conference` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Уникальный идентификатор конференции. Классически является первичным ключом с типом данных INT.',
  `topic` VARCHAR(255) NOT NULL COMMENT 'Тема конференции. Например, «Международная журналистика-2018: глобальные вызовы, региональное партнерство и медиа». Не является уникальным ключом, т.к. существуют конференции, которые могут проводиться каждый год на одну и ту же тему. ',
  `number_of_participants` SMALLINT UNSIGNED NOT NULL COMMENT 'Количество участников конференции. Поле необходимо для контроля количества заявок на конференцию. Обязательное.',
  `place` VARCHAR(60) NOT NULL,
  `date_start` DATETIME NOT NULL COMMENT 'Дата и время начала конференции. Обязательное поле.',
  `date_end` DATETIME NOT NULL COMMENT 'Дата и время окончания конференции. Конференции могут длиться несколько часов, а могут и несколько дней. ',
  `deadline` DATETIME NOT NULL COMMENT 'Дедлайн подачи заявок на участие в конференции.',
  PRIMARY KEY (`id_conference`)  COMMENT 'Первичный ключ',
  INDEX `date` (`date_start` ASC)  COMMENT 'удобен для отбора конференций, которые проходят сейчас или же только планируются.')
ENGINE = InnoDB
COMMENT = 'Содержит список конференций, которые проводятся,планируются или проводились ранее.';


-- -----------------------------------------------------
-- Table `conference`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `conference`.`user` (
  `login`    VARCHAR(30)  NOT NULL COMMENT 'Логин-первичный ключ-уникальный идентификатор пользователя.',
  `password` VARCHAR(255) NOT NULL
  COMMENT 'Пароль пользователя для входа в систему. Обязательное для заполнения поле.',
  `email`    VARCHAR(255) NOT NULL COMMENT 'Email- еще один уникальный идентификатор пользователя. Обязательное поле, т.к. по нему система сможет проводить операции по восстановлению пароля, отсылке рекламы и проч.',
  `type`     CHAR(5)      NOT NULL DEFAULT 'user' COMMENT 'Тип аккаунта-может быть \'user\' или \'admin\'. Необходимо для разграничения прав пользования системой.',
  PRIMARY KEY (`login`)  COMMENT 'Первичный ключ',
  UNIQUE INDEX `email_UNIQUE` (`email` ASC)  COMMENT 'email-Уникальный идентификатор пользователя помимо первичного ключа.')
ENGINE = InnoDB
COMMENT = 'Содержит список пользователей системы.';


-- -----------------------------------------------------
-- Table `conference`.`participant`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `conference`.`participant` (
  `login` VARCHAR(30) NOT NULL COMMENT 'Поле из таблицы user',
  `surname` VARCHAR(35) NOT NULL COMMENT 'Фамилия участника конференции. ',
  `name` VARCHAR(35) NOT NULL COMMENT 'Имя участника конференции.',
  `scope` VARCHAR(100) NOT NULL COMMENT 'Сфера деятельности участника конференции. Поле необходимо для администратора, в чьи обязанности входит проверка заявки на участие. Т.е. если сфера деятельности возможного участника конференции не совпадает с ее тематикой, то заявка будет отклонена.',
  `position` VARCHAR(50) NULL COMMENT 'Должность, которую занимает в жизни участник конференции.',
  `company` VARCHAR(50) NULL COMMENT 'Компания, в которой работает участник конференции.',
  PRIMARY KEY (`login`)  COMMENT 'Первичный ключ',
  INDEX `scope` (`scope` ASC)  COMMENT 'Индекс, для удобного поиска данных. Например,можно делать выборку по сфере деятельности участников, получить всех участников из сферы ИТ и предложить им участие в конференции.',
  INDEX `fk_participant_user` (`login` ASC)  COMMENT 'Внешний ключ таблицы user',
  CONSTRAINT `fk_participant_user1`
    FOREIGN KEY (`login`)
    REFERENCES `conference`.`user` (`login`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = 'Содержит список участников конференции-пользователей системы.';


-- -----------------------------------------------------
-- Table `conference`.`entry`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `conference`.`entry` (
  `id_entry` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Уникальный идентификатор заявки на участие в конференции. Классически первичный ключ с типом данных INT.',
  `login`    VARCHAR(30)  NOT NULL COMMENT 'Внешний ключ',
  `status`   CHAR(11)     NOT NULL DEFAULT 'Waiting'
  COMMENT 'Текущий статус заявки. Если заявка подана, но не проверена администратором, то \"Ожидает\". Так же может быть \"Отклонена\" в случае отказа от участия или несоответствия тематике конференции сферы деятельности участника.  \"Одобрена\", если заявка прошла проверку администратором успешно.',
  PRIMARY KEY (`id_entry`)  COMMENT 'Первичный ключ',
  INDEX `status` (`status` ASC)  COMMENT 'Удобен для отбора заявок, которые ожидают проверки.',
  INDEX `fk_entry_participant` (`login` ASC)  COMMENT 'Внешний ключ.',
  CONSTRAINT `fk_entry_participant1`
    FOREIGN KEY (`login`)
    REFERENCES `conference`.`participant` (`login`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = 'Содержит список заявок в какие-либо секции. ';


-- -----------------------------------------------------
-- Table `conference`.`question`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `conference`.`question` (
  `id_question` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Каждый вопрос обладает уникальным идентификатором, который является первичным ключом. Классически тип INT.',
  `login`       VARCHAR(30) NOT NULL COMMENT 'Внешний ключ из таблицы user. ',
  `question`    VARCHAR(255) NOT NULL COMMENT 'Поле \"вопрос\". Здесь хранится вопрос, который пользователь может задать администратору. Максимальное количество символов-255.',
  `answer`      VARCHAR(255) COMMENT 'Поле хранит ответ администратора на наиболее часто задаваемые вопросы',
  PRIMARY KEY (`id_question`)  COMMENT 'Первичный ключ',
  INDEX `fk_question_user` (`login` ASC)  COMMENT 'Внешний ключ',
  CONSTRAINT `fk_question_user`
    FOREIGN KEY (`login`)
    REFERENCES `conference`.`user` (`login`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = 'Содержит список вопросов от пользователей и гостей системы.';


-- -----------------------------------------------------
-- Table `conference`.`section`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `conference`.`section` (
  `id_section` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Уникальный идентификатор секции. Классически первичный ключ с типом данных INT.',
  `id_conference` INT UNSIGNED NOT NULL COMMENT 'Поле из таблицы conference',
  `title` VARCHAR(255) NOT NULL COMMENT 'Название секции. Например, «Математическое моделирование и компьютерная графика». Обязательное поле.',
  PRIMARY KEY (`id_section`)  COMMENT 'Первичный ключ',
  INDEX `fk_section_conference` (`id_conference` ASC)  COMMENT 'Внешний ключ таблицы conference',
  CONSTRAINT `fk_section_conference1`
    FOREIGN KEY (`id_conference`)
    REFERENCES `conference`.`conference` (`id_conference`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = 'Содержит список секций конференции.';


-- -----------------------------------------------------
-- Table `conference`.`sectionentry`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `conference`.`sectionentry` (
  `id_section` INT UNSIGNED NOT NULL COMMENT 'Поле из таблицы section',
  `id_entry` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id_section`, `id_entry`)  COMMENT 'Первичный ключ',
  INDEX `fk_sectionentry_section` (`id_section` ASC)  COMMENT 'Внешний ключ',
  INDEX `fk_sectionentry_entry` (`id_entry` ASC),
  CONSTRAINT `fk_sectionentry_section`
    FOREIGN KEY (`id_section`)
    REFERENCES `conference`.`section` (`id_section`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_sectionentry_entry`
    FOREIGN KEY (`id_entry`)
    REFERENCES `conference`.`entry` (`id_entry`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = 'Таблица для реализации связи \"многие ко многим\"';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `conference`.`conference`
-- -----------------------------------------------------
START TRANSACTION;
USE `conference`;
INSERT INTO `conference`.`conference` (`id_conference`, `topic`, `number_of_participants`, `place`, `date_start`, `date_end`, `deadline`)
VALUES (1, 'Business Internet', 200, 'Business-center \"Celsius\"', '2018.11.11', '2018.11.13', '2018.11.05');
INSERT INTO `conference`.`conference` (`id_conference`, `topic`, `number_of_participants`, `place`, `date_start`, `date_end`, `deadline`)
VALUES (2, 'Annual Autumn Conference of Otorhinolaryngologists of the Republic of Belarus', 50, 'BSMU', '2018.10.10',
        '2018.10.11', '2018.10.08');
INSERT INTO `conference`.`conference` (`id_conference`, `topic`, `number_of_participants`, `place`, `date_start`, `date_end`, `deadline`)
VALUES (3, 'Innovative technologies, automation and mechatronics in machine and instrument engineering', 110,
        'Concert Hall \"Tractor\"', '2018.12.15', '2018.12.16', '2018.12.10');
INSERT INTO `conference`.`conference` (`id_conference`, `topic`, `number_of_participants`, `place`, `date_start`, `date_end`, `deadline`)
VALUES
  (4, 'International journalism-2018: global challenges, regional partnership and media', 70, 'Open Space \"Space\"',
   '2018.12.18', '2018.12.19', '2018.12.15');
INSERT INTO `conference`.`conference` (`id_conference`, `topic`, `number_of_participants`, `place`, `date_start`, `date_end`, `deadline`)
VALUES
  (5, 'Corporate strategic communications: new trends in professional activity', 300, 'Hotel \"Moscow\"', '2018.01.15',
   '2018.01.25', '2018.01.10');
INSERT INTO `conference`.`conference` (`id_conference`, `topic`, `number_of_participants`, `place`, `date_start`, `date_end`, `deadline`)
VALUES (6, 'Christian values ​​in the culture of modern youth', 30, 'Hotel \"Victoria\"', '2018.12.25', '2018.12.25',
        '2018.12.23');
INSERT INTO `conference`.`conference` (`id_conference`, `topic`, `number_of_participants`, `place`, `date_start`, `date_end`, `deadline`)
VALUES
  (7, 'Modern Education: World Trends and Regional Aspects', 100, 'Main building of BSU', '2018.01.27', '2018.01.28',
   '2018.01.25');
INSERT INTO `conference`.`conference` (`id_conference`, `topic`, `number_of_participants`, `place`, `date_start`, `date_end`, `deadline`)
VALUES
  (8, 'Modern management: problems, studies, prospects', 250, 'Business-center \"Titan\"', '2018.02.02', '2018.02.05',
   '2018.01.29');
INSERT INTO `conference`.`conference` (`id_conference`, `topic`, `number_of_participants`, `place`, `date_start`, `date_end`, `deadline`)
VALUES (9, 'Media space of Belarus: history and modernity', 500, 'Business-center \"Sail\"', '2018.02.10', '2018.02.12',
        '2018.02.05');
INSERT INTO `conference`.`conference` (`id_conference`, `topic`, `number_of_participants`, `place`, `date_start`, `date_end`, `deadline`)
VALUES (10, 'Problems and prospects of e-business', 150, 'Business-center \"Renaissance\"', '2018.11.12', '2018.11.15',
        '2018.11.07');

COMMIT;


-- -----------------------------------------------------
-- Data for table `conference`.`user`
-- -----------------------------------------------------
START TRANSACTION;
USE `conference`;
INSERT INTO `conference`.`user` (`login`, `password`, `email`, `type`)
VALUES ('admin', 'PgeP3eYWeQyllnrC3tfh+rK37Jf8wWgM', 'kvachov@gmail.com', 'admin');
INSERT INTO `conference`.`user` (`login`, `password`, `email`, `type`)
VALUES ('popo83', 'PnTszvu7UuA6TGbmmAsx53F2MAIY2Cyo', 'polinanikitina@mail.ru', 'user');
INSERT INTO `conference`.`user` (`login`, `password`, `email`, `type`)
VALUES ('qwerty4', 'DAlMe8eVzQ/33lkZu2Q88ZJSbwGUbo+V', 'allagrish55@yandex.ru', 'user');
INSERT INTO `conference`.`user` (`login`, `password`, `email`, `type`)
VALUES ('zver\'', 'owXgcXervYGzMLirof4yUNtnQsKB+xL7', 'va3zver@tut.by', 'user');
INSERT INTO `conference`.`user` (`login`, `password`, `email`, `type`)
VALUES ('science_man', 'vI+DEKeO4PIUu7zj0ct6KLb6IAN0ioEu', 'evgenbatikov@gmail.com', 'user');
INSERT INTO `conference`.`user` (`login`, `password`, `email`, `type`)
VALUES ('meneger3', '5RkVyZ6c3NIQkg/rZnIFJ7qEl6kJFe/2', 'illyavishia@mail.ru', 'user');
INSERT INTO `conference`.`user` (`login`, `password`, `email`, `type`)
VALUES ('rock4', '5Ke0t4oxpj2YYq3YSgMg9FPyED+Q2sKo', 'dianarock@yandex.ru', 'user');

COMMIT;


-- -----------------------------------------------------
-- Data for table `conference`.`participant`
-- -----------------------------------------------------
START TRANSACTION;
USE `conference`;
INSERT INTO `conference`.`participant` (`login`, `surname`, `name`, `scope`, `position`, `company`)
VALUES ('zver\'', 'Zverkiv', 'Ihor', 'Management', 'Sales Manager', 'SoftScience');
INSERT INTO `conference`.`participant` (`login`, `surname`, `name`, `scope`, `position`, `company`)
VALUES ('popo83', 'Nikitina', 'Polina', 'Medicine', 'Chief Physician', 'Central City Hospital No.2');
INSERT INTO `conference`.`participant` (`login`, `surname`, `name`, `scope`, `position`, `company`)
VALUES ('science_man', 'Batikov', 'Eugen', 'Phylosophy', 'Senior Lecturer', 'BSU');
INSERT INTO `conference`.`participant` (`login`, `surname`, `name`, `scope`, `position`, `company`)
VALUES ('meneger3', 'Ilya', 'Vyshevsky', 'Economics', 'Economist', NULL);
INSERT INTO `conference`.`participant` (`login`, `surname`, `name`, `scope`, `position`, `company`)
VALUES ('rock4', 'Diana', 'Rockova', 'Bussines', 'Individual entrepreneur', NULL);
INSERT INTO `conference`.`participant` (`login`, `surname`, `name`, `scope`, `position`, `company`)
VALUES ('qwerty4', 'Alla', 'Grushkovets', 'Journalism', 'Journalist', 'Newspaper \"Pravda\"');

COMMIT;


-- -----------------------------------------------------
-- Data for table `conference`.`entry`
-- -----------------------------------------------------
START TRANSACTION;
USE `conference`;
INSERT INTO `conference`.`entry` (`id_entry`, `login`, `status`) VALUES (1, 'popo83', 'Approved');
INSERT INTO `conference`.`entry` (`id_entry`, `login`, `status`) VALUES (2, 'rock4', 'Waiting');
INSERT INTO `conference`.`entry` (`id_entry`, `login`, `status`) VALUES (3, 'rock4', 'Disapproved');
INSERT INTO `conference`.`entry` (`id_entry`, `login`, `status`) VALUES (4, 'meneger3', 'Waiting');
INSERT INTO `conference`.`entry` (`id_entry`, `login`, `status`) VALUES (5, 'qwerty4', 'Approved');
INSERT INTO `conference`.`entry` (`id_entry`, `login`, `status`) VALUES (6, 'science_man', 'Disapproved');
INSERT INTO `conference`.`entry` (`id_entry`, `login`, `status`) VALUES (7, 'science_man', 'Approved');
INSERT INTO `conference`.`entry` (`id_entry`, `login`, `status`) VALUES (8, 'popo83', 'Waiting');
INSERT INTO `conference`.`entry` (`id_entry`, `login`, `status`) VALUES (9, 'qwerty4', 'Waiting');
INSERT INTO `conference`.`entry` (`id_entry`, `login`, `status`) VALUES (10, 'meneger3', 'Approved');
INSERT INTO `conference`.`entry` (`id_entry`, `login`, `status`) VALUES (11, 'popo83', 'Disapproved');

COMMIT;


-- -----------------------------------------------------
-- Data for table `conference`.`question`
-- -----------------------------------------------------
START TRANSACTION;
USE `conference`;
INSERT INTO `conference`.`question` (`id_question`, `login`, `question`, `answer`)
VALUES (1, 'zver\'', 'When is the marketing conference expected?', 'In the near future it is not planned');
INSERT INTO `conference`.`question` (`id_question`, `login`, `question`, `answer`) VALUES
  (2, 'meneger3', 'Can I delete a conference application?',
   'You can do this in your account. Opposite to each application there is a button \"Cancel application\" ');
INSERT INTO `conference`.`question` (`id_question`, `login`, `question`, `answer`)
VALUES (3, 'popo83', 'Is participation in conferences free?', 'Yes');
INSERT INTO `conference`.`question` (`id_question`, `login`, `question`, `answer`) VALUES
  (4, 'zver\'', 'Is it possible to come to several people on one application?',
   'No, each participant must be registered');

COMMIT;


-- -----------------------------------------------------
-- Data for table `conference`.`section`
-- -----------------------------------------------------
START TRANSACTION;
USE `conference`;
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`) VALUES (1, 1, 'Media');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`)
VALUES (2, 3, 'Industrial robotics and mechatronics');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`) VALUES (3, 1, 'Marketing');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`) VALUES (4, 1, 'Business');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`)
VALUES (5, 10, 'E-business hardware and software');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`) VALUES (6, 2, 'Somnology');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`)
VALUES (7, 6, ' Christian values ​​in contemporary art');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`)
VALUES (8, 4, 'The economic belt of the Silk Road in media reflection');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`)
VALUES (9, 7, ' Scientific and methodological support of innovative education');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`)
VALUES (10, 9, 'Priority issues of print media');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`) VALUES
  (11, 5, 'Public relations, advertising and journalism as types of mass information and communication activities');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`)
VALUES (12, 8, 'National Economy and Public Administration');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`) VALUES (13, 2, 'Laryngology');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`)
VALUES (14, 7, 'Additional education for children and youth as a resource for personal development');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`) VALUES (15, 5,
                                                                                    'Methodical features of teaching advertising and PR-communication in the framework of higher and postgraduate education: problems and prospects');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`)
VALUES (16, 3, 'CAD in machine and instrument engineering');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`)
VALUES (17, 9, 'Problems of national statehood in the media');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`)
VALUES (18, 10, 'Economics of e-business: state and development prospects');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`)
VALUES (19, 4, 'International journalism and the Internet');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`)
VALUES (20, 6, 'Christian Values ​​and Actual Problems of Ecology');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`)
VALUES (21, 8, 'Problems and prospects of increasing the economic efficiency of the enterprise');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`) VALUES (22, 2, 'Rhinology');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`) VALUES (23, 5,
                                                                                    'Practical orientation of training specialists in the field of public relations and advertising: market needs, the view of employers');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`)
VALUES (24, 9, 'Trends in the development of modern media');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`)
VALUES (25, 3, 'High-energy technologies for obtaining and processing materials. Surface Engineering');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`)
VALUES (26, 8, 'Mathematical methods and information technologies in management');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`)
VALUES (27, 4, 'International journalism and foreign advertising: the potential for interaction');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`)
VALUES (28, 6, 'Actual problems of modern theology');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`)
VALUES (29, 7, 'Problems of development and use of electronic educational resources');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`)
VALUES (30, 10, 'Topical issues of training specialists in the field of electronic business');

COMMIT;


-- -----------------------------------------------------
-- Data for table `conference`.`sectionentry`
-- -----------------------------------------------------
START TRANSACTION;
USE `conference`;
INSERT INTO `conference`.`sectionentry` (`id_section`, `id_entry`) VALUES (6, 1);
INSERT INTO `conference`.`sectionentry` (`id_section`, `id_entry`) VALUES (13, 1);
INSERT INTO `conference`.`sectionentry` (`id_section`, `id_entry`) VALUES (2, 3);
INSERT INTO `conference`.`sectionentry` (`id_section`, `id_entry`) VALUES (7, 2);
INSERT INTO `conference`.`sectionentry` (`id_section`, `id_entry`) VALUES (10, 5);
INSERT INTO `conference`.`sectionentry` (`id_section`, `id_entry`) VALUES (17, 5);
INSERT INTO `conference`.`sectionentry` (`id_section`, `id_entry`) VALUES (15, 3);
INSERT INTO `conference`.`sectionentry` (`id_section`, `id_entry`) VALUES (1, 10);
INSERT INTO `conference`.`sectionentry` (`id_section`, `id_entry`) VALUES (3, 10);
INSERT INTO `conference`.`sectionentry` (`id_section`, `id_entry`) VALUES (4, 10);
INSERT INTO `conference`.`sectionentry` (`id_section`, `id_entry`) VALUES (21, 8);
INSERT INTO `conference`.`sectionentry` (`id_section`, `id_entry`) VALUES (26, 8);
INSERT INTO `conference`.`sectionentry` (`id_section`, `id_entry`) VALUES (18, 4);
INSERT INTO `conference`.`sectionentry` (`id_section`, `id_entry`) VALUES (30, 4);
INSERT INTO `conference`.`sectionentry` (`id_section`, `id_entry`) VALUES (16, 6);
INSERT INTO `conference`.`sectionentry` (`id_section`, `id_entry`) VALUES (28, 7);
INSERT INTO `conference`.`sectionentry` (`id_section`, `id_entry`) VALUES (20, 7);
INSERT INTO `conference`.`sectionentry` (`id_section`, `id_entry`) VALUES (21, 9);
INSERT INTO `conference`.`sectionentry` (`id_section`, `id_entry`) VALUES (12, 9);
INSERT INTO `conference`.`sectionentry` (`id_section`, `id_entry`) VALUES (10, 11);
INSERT INTO `conference`.`sectionentry` (`id_section`, `id_entry`) VALUES (17, 11);

COMMIT;

