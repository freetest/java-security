/*
 Navicat Premium Data Transfer

 Source Server         : docker_mysql_01
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : 192.168.99.100:33306
 Source Schema         : springdemo

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 02/12/2021 13:57:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for student_score
-- ----------------------------
DROP TABLE IF EXISTS `student_score`;
CREATE TABLE `student_score`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `score` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student_score
-- ----------------------------
INSERT INTO `student_score` VALUES (1, '张三', 90);
INSERT INTO `student_score` VALUES (2, '李四', 75);
INSERT INTO `student_score` VALUES (3, '王五', 61);
INSERT INTO `student_score` VALUES (4, '赵六', 59);

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES (1, '本人订单', 110.00, '收件地址:深圳市福田区xxx街道', '收件人手机:1381234567', '收件人:张三');
INSERT INTO `t_order` VALUES (2, '其他人订单', 123.00, '收件地址:深圳市南山区xxx街道', '收件人手机:18800000000', '收件人:李四');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `age` int NULL DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1465944647401578499 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'Jone', 18, 'test1@baomidou.com', 'YWfYNzSyXFS/7ojYXmnKNQ==');
INSERT INTO `t_user` VALUES (2, 'Jack', 20, 'test2@baomidou.com', NULL);
INSERT INTO `t_user` VALUES (3, 'Tom', 28, 'test3@baomidou.com', NULL);
INSERT INTO `t_user` VALUES (4, 'Sandy', 21, 'test4@baomidou.com', NULL);
INSERT INTO `t_user` VALUES (5, 'Billie', 24, 'test5@baomidou.com', NULL);
INSERT INTO `t_user` VALUES (6, '孔子', 1000, 'kongzi@126.com', NULL);
INSERT INTO `t_user` VALUES (1465944647401578498, 'leilei', 22, 'ancd@123.com', 'xmh2fVZNlxL31xdpnOoU52LN6CSUYstT');

SET FOREIGN_KEY_CHECKS = 1;
