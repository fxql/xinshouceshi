/*
 Navicat Premium Data Transfer

 Source Server         : lhr
 Source Server Type    : MySQL
 Source Server Version : 80034
 Source Host           : localhost:3306
 Source Schema         : storemanage

 Target Server Type    : MySQL
 Target Server Version : 80034
 File Encoding         : 65001

 Date: 10/11/2023 12:04:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for instock
-- ----------------------------
DROP TABLE IF EXISTS `instock`;
CREATE TABLE `instock`  (
  `ID` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `supname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `stockname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `intime` date NULL DEFAULT NULL,
  `num` int NULL DEFAULT NULL,
  `pric` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `user` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Triggers structure for table instock
-- ----------------------------
DROP TRIGGER IF EXISTS `addindata1`;
delimiter ;;
CREATE TRIGGER `addindata1` BEFORE INSERT ON `instock` FOR EACH ROW BEGIN
	
	UPDATE product SET stock=stock+new.num WHERE product.`name`= new.stockname and product.supname=new.supname ;
	end
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
