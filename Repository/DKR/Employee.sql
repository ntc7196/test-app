CREATE TABLE `dkr_dev`.`Employee` (
  `UUID` VARCHAR(64) NOT NULL,
  `e_name` VARCHAR(32) NOT NULL,
  `e_dept` VARCHAR(128) NOT NULL,
  `e_phone` VARCHAR(15) NOT NULL,
  `e_address` VARCHAR(128) NULL,
  PRIMARY KEY (`UUID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

INSERT INTO `dkr_dev`.`Employee`
(`UUID`,
`e_name`,
`e_dept`,
`e_phone`,
`e_address`)
VALUES
(uuid(),
'Amanda',
'Risk & Finace',
'012345656',
'Cambodia');

INSERT INTO `dkr_dev`.`Employee`
(`UUID`,
`e_name`,
`e_dept`,
`e_phone`,
`e_address`)
VALUES
(uuid(),
'Patrick',
'Global control & monitor',
'084345656',
'Palestine');

INSERT INTO `dkr_dev`.`Employee`
(`UUID`,
`e_name`,
`e_dept`,
`e_phone`,
`e_address`)
VALUES
(uuid(),
'Christine',
'Human Resources',
'012935656',
'Japan');

INSERT INTO `dkr_dev`.`Employee`
(`UUID`,
`e_name`,
`e_dept`,
`e_phone`,
`e_address`)
VALUES
(uuid(),
'Marisa',
'IT Department',
'015555656',
'United State');

INSERT INTO `dkr_dev`.`Employee`
(`UUID`,
`e_name`,
`e_dept`,
`e_phone`,
`e_address`)
VALUES
(uuid(),
'Jean',
'Director',
'066665656',
'Brunei');

INSERT INTO `dkr_dev`.`Employee`
(`UUID`,
`e_name`,
`e_dept`,
`e_phone`,
`e_address`)
VALUES
(uuid(),
'Alexandre',
'Migration',
'012300656',
'ThaiLand');