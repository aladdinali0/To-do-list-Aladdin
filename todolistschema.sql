CREATE DATABASE  IF NOT EXISTS `todolistschema`;
USE `todolistschema`;

--
-- Table structure for table `todolistproject`
--

DROP TABLE IF EXISTS `todolistproject`;

CREATE TABLE `todolistproject` (
  `id` int AUTO_INCREMENT PRIMARY KEY,
  `task_name`varchar(255) NOT NULL,
  `description` varchar(255) ,
  `completed` BOOLEAN DEFAULT FALSE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

