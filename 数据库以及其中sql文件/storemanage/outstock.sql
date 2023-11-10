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

 Date: 10/11/2023 12:04:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for outstock
-- ----------------------------
DROP TABLE IF EXISTS `outstock`;
CREATE TABLE `outstock`  (
  `ID` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `supname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `stockname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `outtime` date NULL DEFAULT NULL,
  `num` int NULL DEFAULT NULL,
  `pri` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `user` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Triggers structure for table outstock
-- ----------------------------
DROP TRIGGER IF EXISTS `addindata2`;
delimiter ;;
CREATE TRIGGER `addindata2` BEFORE INSERT ON `outstock` FOR EACH ROW BEGIN
	
	UPDATE product SET stock=stock-new.num WHERE product.`name`= new.stockname and product.supname=new.supname ;
	end
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table outstock
-- ----------------------------
DROP TRIGGER IF EXISTS `outint1`;
delimiter ;;
CREATE TRIGGER `outint1` AFTER INSERT ON `outstock` FOR EACH ROW DELETE  FROM instock a where exists (select 1 from outstock b where a.id=b.id )
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
