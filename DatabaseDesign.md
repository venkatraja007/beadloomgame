# Database Design #

Information about the database, currently hosted on playground.
database name: BeadLoomGame

CREATE TABLE  `unccmake_CSDT`.`Users` (
`user` VARCHAR( 20 ) NOT NULL ,
`password` VARCHAR( 20 ) NOT NULL ,
PRIMARY KEY (  `user` )
) ENGINE = MYISAM ;

CREATE TABLE
`BeadLoomGame`.`HighScores` (
`user` VARCHAR( 20 ) NOT NULL ,
`score` INT NOT NULL ,
`time` VARCHAR( 10 ) NOT NULL ,
`medal` VARCHAR( 20 ) NOT NULL ,
`puzzle` VARCHAR( 30 ) NOT NULL ,
`timeStamp` TIMESTAMP ON UPDATE CURRENT\_TIMESTAMP NOT NULL DEFAULT CURRENT\_TIMESTAMP ,
PRIMARY KEY (  `user` ,  `puzzle` )

SQL Insert Query:
INSERT INTO  `BeadLoomGame`.`HighScores` (
`user` ,
`score` ,
`time` ,
`medal` ,
`puzzle` ,
`timeStamp`
)
VALUES (
'Acey',  '10',  '2:10',  'Platinum',  'Batman', NOW( )
);

## Users Table ##
Table Schema:
| **user** | **password(md5)** |
|:---------|:------------------|

## High Scores Table ##

Should be grouped by puzzle upon display
Time should be used for tie-breakers

Display Schema(per puzzle):
| **User Name** | **Score** | **Time** | **Medal** |
|:--------------|:----------|:---------|:----------|
| Acey | 10 | 2:10 |  Platinum |

Table Schema:
primary keys: user name & puzzle, keeping only the best score on each puzzle
| **user** | **score** | **time** | **medal** | **puzzle** | **timeStamp** |
|:---------|:----------|:---------|:----------|:-----------|:--------------|