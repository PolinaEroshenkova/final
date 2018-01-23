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
  `login` VARCHAR(30) NOT NULL COMMENT 'Логин-первичный ключ-уникальный идентификатор пользователя.',
  `password` VARCHAR(30) NOT NULL COMMENT 'Пароль пользователя для входа в систему. Обязательное для заполнения поле.',
  `email` VARCHAR(255) NOT NULL COMMENT 'Email- еще один уникальный идентификатор пользователя. Обязательное поле, т.к. по нему система сможет проводить операции по восстановлению пароля, отсылке рекламы и проч.',
  `type` CHAR(5) NOT NULL DEFAULT 'user' COMMENT 'Тип аккаунта-может быть \'user\' или \'admin\'. Необходимо для разграничения прав пользования системой.',
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
  `login` VARCHAR(30) NOT NULL COMMENT 'Внешний ключ',
  `status` CHAR(10) NOT NULL DEFAULT 'Ожидает' COMMENT 'Текущий статус заявки. Если заявка подана, но не проверена администратором, то \"Ожидает\". Так же может быть \"Отклонена\" в случае отказа от участия или несоответствия тематике конференции сферы деятельности участника.  \"Одобрена\", если заявка прошла проверку администратором успешно.',
  PRIMARY KEY (`id_entry`)  COMMENT 'Первичный ключ',
  INDEX `status` (`status` ASC)  COMMENT 'Удобен для отбора заявок, которые ожидают проверки.',
  INDEX `fk_entry_participant` (`login` ASC)  COMMENT 'Внешний ключ.',
  CONSTRAINT `fk_entry_participant1`
    FOREIGN KEY (`login`)
    REFERENCES `conference`.`participant` (`login`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = 'Содержит список заявок в какие-либо секции. ';


-- -----------------------------------------------------
-- Table `conference`.`question`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `conference`.`question` (
  `id_question` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Каждый вопрос обладает уникальным идентификатором, который является первичным ключом. Классически тип INT.',
  `login` VARCHAR(30) NOT NULL COMMENT 'Внешний ключ из таблицы user. ',
  `question` VARCHAR(255) NOT NULL COMMENT 'Поле \"вопрос\". Здесь хранится вопрос, который пользователь может задать администратору. Максимальное количество символов-255.',
  PRIMARY KEY (`id_question`)  COMMENT 'Первичный ключ',
  INDEX `fk_question_user` (`login` ASC)  COMMENT 'Внешний ключ',
  CONSTRAINT `fk_question_user`
    FOREIGN KEY (`login`)
    REFERENCES `conference`.`user` (`login`)
    ON DELETE NO ACTION
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
INSERT INTO `conference`.`conference` (`id_conference`, `topic`, `number_of_participants`, `place`, `date_start`, `date_end`, `deadline`) VALUES (1, 'Деловой интернет', 200, 'Бизнес-центр \"Цельсий\"', '2017.11.11', '2017.11.13', '2017.11.05');
INSERT INTO `conference`.`conference` (`id_conference`, `topic`, `number_of_participants`, `place`, `date_start`, `date_end`, `deadline`) VALUES (2, 'Ежегодная осенняя конференция отоларингологов РБ', 50, 'БГМУ', '2017.10.10', '2017.10.11', '2017.10.08');
INSERT INTO `conference`.`conference` (`id_conference`, `topic`, `number_of_participants`, `place`, `date_start`, `date_end`, `deadline`) VALUES (3, 'Инновационные технологии, автоматизация и мехатроника в машино- и приборостроении', 110, 'Концертный зал \"Трактор\"', '2017.12.15', '2017.12.16', '2017.12.10');
INSERT INTO `conference`.`conference` (`id_conference`, `topic`, `number_of_participants`, `place`, `date_start`, `date_end`, `deadline`) VALUES (4, 'Международная журналистика-2018: глобальные вызовы, региональное партнерство и медиа', 70, 'Open Space \"Space\"', '2017.12.18', '2017.12.19', '2017.12.15');
INSERT INTO `conference`.`conference` (`id_conference`, `topic`, `number_of_participants`, `place`, `date_start`, `date_end`, `deadline`) VALUES (5, 'Корпоративные стратегические коммуникации: новые тренды в профессиональной деятельности', 300, 'Отель \"Москва\"', '2018.01.15', '2018.01.25', '2018.01.10');
INSERT INTO `conference`.`conference` (`id_conference`, `topic`, `number_of_participants`, `place`, `date_start`, `date_end`, `deadline`) VALUES (6, 'Христианские ценности в культуре современной молодёжи', 30, 'Гостиница \"Виктория\"', '2017.12.25', '2017.12.25', '2017.12.23');
INSERT INTO `conference`.`conference` (`id_conference`, `topic`, `number_of_participants`, `place`, `date_start`, `date_end`, `deadline`) VALUES (7, 'Современное образование: мировые тенденции и региональные аспекты', 100, 'Главный корпус БГУ', '2018.01.27', '2018.01.28', '2018.01.25');
INSERT INTO `conference`.`conference` (`id_conference`, `topic`, `number_of_participants`, `place`, `date_start`, `date_end`, `deadline`) VALUES (8, 'Современный менеджмент: проблемы, исследования, перспективы', 250, 'Бизнес-центр \"Титан\"', '2018.02.02', '2018.02.05', '2018.01.29');
INSERT INTO `conference`.`conference` (`id_conference`, `topic`, `number_of_participants`, `place`, `date_start`, `date_end`, `deadline`) VALUES (9, 'Медиапространство Беларуси: история и современность', 500, 'Бизнес-центр \"Парус\"', '2018.02.10', '2018.02.12', '2018.02.05');
INSERT INTO `conference`.`conference` (`id_conference`, `topic`, `number_of_participants`, `place`, `date_start`, `date_end`, `deadline`) VALUES (10, 'Проблемы и перспективы электронного бизнеса', 150, 'Бизнес-центр \"Ренессанс\"', '2017.11.12', '2017.11.15', '2017.11.07');

COMMIT;


-- -----------------------------------------------------
-- Data for table `conference`.`user`
-- -----------------------------------------------------
START TRANSACTION;
USE `conference`;
INSERT INTO `conference`.`user` (`login`, `password`, `email`, `type`) VALUES ('admin', 'admin', 'kvachov@gmail.com', 'admin');
INSERT INTO `conference`.`user` (`login`, `password`, `email`, `type`) VALUES ('popo83', 'pol123', 'polinanikitina@mail.ru', 'user');
INSERT INTO `conference`.`user` (`login`, `password`, `email`, `type`) VALUES ('qwerty4', '1Alla!', 'allagrish55@yandex.ru', 'user');
INSERT INTO `conference`.`user` (`login`, `password`, `email`, `type`) VALUES ('zver\'', 'ranetki13', 'va3zver@tut.by', 'user');
INSERT INTO `conference`.`user` (`login`, `password`, `email`, `type`) VALUES ('science_man', 'ZhEnIa1', 'evgenbatikov@gmail.com', 'user');
INSERT INTO `conference`.`user` (`login`, `password`, `email`, `type`) VALUES ('meneger3', '72558305', 'illyavishia@mail.ru', 'user');
INSERT INTO `conference`.`user` (`login`, `password`, `email`, `type`) VALUES ('rock4', 'ZxcasdqwE', 'dianarock@yandex.ru', 'user');

COMMIT;


-- -----------------------------------------------------
-- Data for table `conference`.`participant`
-- -----------------------------------------------------
START TRANSACTION;
USE `conference`;
INSERT INTO `conference`.`participant` (`login`, `surname`, `name`, `scope`, `position`, `company`) VALUES ('zver\'', 'Зверков', 'Игорь', 'менеджмент', 'Менеджер по продажам', 'SoftScience');
INSERT INTO `conference`.`participant` (`login`, `surname`, `name`, `scope`, `position`, `company`) VALUES ('popo83', 'Никитина', 'Полина', 'медицина', 'Глав. врач', 'Центральная городская больница №2');
INSERT INTO `conference`.`participant` (`login`, `surname`, `name`, `scope`, `position`, `company`) VALUES ('science_man', 'Батиков', 'Евгений', 'философия', 'Старший преподаватель', 'БГУ');
INSERT INTO `conference`.`participant` (`login`, `surname`, `name`, `scope`, `position`, `company`) VALUES ('meneger3', 'Илья', 'Вишневский', 'экономика', 'Экономист', NULL);
INSERT INTO `conference`.`participant` (`login`, `surname`, `name`, `scope`, `position`, `company`) VALUES ('rock4', 'Диана', 'Рокова', 'бизнес', 'ИП', NULL);
INSERT INTO `conference`.`participant` (`login`, `surname`, `name`, `scope`, `position`, `company`) VALUES ('qwerty4', 'Алла', 'Гришковец', 'журналистика', 'Журналист', 'Газета \"Правда\"');

COMMIT;


-- -----------------------------------------------------
-- Data for table `conference`.`entry`
-- -----------------------------------------------------
START TRANSACTION;
USE `conference`;
INSERT INTO `conference`.`entry` (`id_entry`, `login`, `status`) VALUES (1, 'popo83', 'Одобрено');
INSERT INTO `conference`.`entry` (`id_entry`, `login`, `status`) VALUES (2, 'rock4', 'Ожидает');
INSERT INTO `conference`.`entry` (`id_entry`, `login`, `status`) VALUES (3, 'rock4', 'Отклонено');
INSERT INTO `conference`.`entry` (`id_entry`, `login`, `status`) VALUES (4, 'meneger3', 'Ожидает');
INSERT INTO `conference`.`entry` (`id_entry`, `login`, `status`) VALUES (5, 'qwerty4', 'Одобрено');
INSERT INTO `conference`.`entry` (`id_entry`, `login`, `status`) VALUES (6, 'science_man', 'Отклонено');
INSERT INTO `conference`.`entry` (`id_entry`, `login`, `status`) VALUES (7, 'science_man', 'Одобрено');
INSERT INTO `conference`.`entry` (`id_entry`, `login`, `status`) VALUES (8, 'popo83', 'Ожидает');
INSERT INTO `conference`.`entry` (`id_entry`, `login`, `status`) VALUES (9, 'qwerty4', 'Ожидает');
INSERT INTO `conference`.`entry` (`id_entry`, `login`, `status`) VALUES (10, 'meneger3', 'Одобрено');

COMMIT;


-- -----------------------------------------------------
-- Data for table `conference`.`question`
-- -----------------------------------------------------
START TRANSACTION;
USE `conference`;
INSERT INTO `conference`.`question` (`id_question`, `login`, `question`) VALUES (1, 'zver\'', 'Когда будет конференция по маркетингу?');
INSERT INTO `conference`.`question` (`id_question`, `login`, `question`) VALUES (2, 'science_man', 'Как восстановить пароль?');
INSERT INTO `conference`.`question` (`id_question`, `login`, `question`) VALUES (3, 'meneger3', 'Можно ли удалить заявку на конференцию?');
INSERT INTO `conference`.`question` (`id_question`, `login`, `question`) VALUES (4, 'popo83', 'Участие в конференциях бесплатное?');
INSERT INTO `conference`.`question` (`id_question`, `login`, `question`) VALUES (5, 'qwerty4', 'Не могу изменить пароль. Что делать?');
INSERT INTO `conference`.`question` (`id_question`, `login`, `question`) VALUES (6, 'zver\'', 'Можно ли по одной заявке придти нескольким людям?');
INSERT INTO `conference`.`question` (`id_question`, `login`, `question`) VALUES (7, 'rock4', 'Во сколько закончится конференция по маркетингу?');

COMMIT;


-- -----------------------------------------------------
-- Data for table `conference`.`section`
-- -----------------------------------------------------
START TRANSACTION;
USE `conference`;
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`) VALUES (1, 1, 'Медиа');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`) VALUES (2, 3, 'Промышленная робототехника и мехатроника');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`) VALUES (3, 1, 'Маркетинг');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`) VALUES (4, 1, 'Бизнес');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`) VALUES (5, 10, 'Технические и программные средства системы электронного бизнеса');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`) VALUES (6, 2, 'сомнология');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`) VALUES (7, 6, ' Христианские ценности в современном искусств');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`) VALUES (8, 4, 'Экономический пояс Шелкового пути в отражении медиа');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`) VALUES (9, 7, ' Научно-методическая поддержка инновационного образования');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`) VALUES (10, 9, 'приоритетная проблематика печатных СМИ');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`) VALUES (11, 5, 'Связи с общественностью, реклама и журналистика как виды массовой информационно-коммуникационной деятельности');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`) VALUES (12, 8, 'Национальная экономика и государственное управление');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`) VALUES (13, 2, 'ларингология');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`) VALUES (14, 7, 'Дополнительное образование детей и молодежи как ресурс развития личности');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`) VALUES (15, 5, 'Методические особенности преподавания рекламной и PR-коммуникации в рамках высшего и последипломного образования: проблемы и перспективы');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`) VALUES (16, 3, 'САПР в технологиях машино- и приборостроения');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`) VALUES (17, 9, 'проблемы национальной государственности в СМИ');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`) VALUES (18, 10, 'Экономика электронного бизнеса: состояние и перспективы развития');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`) VALUES (19, 4, 'международная журналистика и интернет');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`) VALUES (20, 6, 'Христианские ценности и актуальные проблемы экологии');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`) VALUES (21, 8, 'Проблемы и перспективы повышения экономической эффективности функционирования предприятия');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`) VALUES (22, 2, 'Ринология');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`) VALUES (23, 5, 'Практикоориентированность подготовки специалистов в сфере связей с общественностью и рекламы: потребности рынка, взгляд  работодателей');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`) VALUES (24, 9, 'тенденции развития современных СМИ');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`) VALUES (25, 3, 'Высокоэнергетические технологии получения и обработки материалов. Инженерия поверхности');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`) VALUES (26, 8, 'Математические методы и информационные технологии в управлении');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`) VALUES (27, 4, 'международная журналистика и зарубежная реклама: потенциал взаимодействия');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`) VALUES (28, 6, 'Актуальные проблемы современной теологии');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`) VALUES (29, 7, 'Проблемы разработки и использования электронных образовательных ресурсов');
INSERT INTO `conference`.`section` (`id_section`, `id_conference`, `title`) VALUES (30, 10, 'Актуальные вопросы подготовки специалистов в области электронного бизнеса');

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

COMMIT;

