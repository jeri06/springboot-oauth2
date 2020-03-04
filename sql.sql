-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               PostgreSQL 9.5.20, compiled by Visual C++ build 1800, 64-bit
-- Server OS:                    
-- HeidiSQL Version:             9.5.0.5282
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES  */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping structure for table public.account
CREATE TABLE IF NOT EXISTS "account" (
	"id" BIGINT NOT NULL COMMENT E'',
	"enabled" BOOLEAN NOT NULL COMMENT E'',
	"password" CHARACTER VARYING(255) NULL DEFAULT NULL COMMENT E'',
	"username" CHARACTER VARYING(255) NULL DEFAULT NULL COMMENT E'',
	PRIMARY KEY ("id"),
	UNIQUE KEY ("username")
);

-- Data exporting was unselected.
-- Dumping structure for table public.account_roles
CREATE TABLE IF NOT EXISTS "account_roles" (
	"account_id" BIGINT NOT NULL COMMENT E'',
	"roles_role_id" BIGINT NOT NULL COMMENT E'',
	PRIMARY KEY ("account_id","roles_role_id")
);

-- Data exporting was unselected.
-- Dumping structure for table public.privilege
CREATE TABLE IF NOT EXISTS "privilege" (
	"privilege_id" BIGINT NOT NULL COMMENT E'',
	"description" CHARACTER VARYING(255) NULL DEFAULT NULL COMMENT E'',
	"name" CHARACTER VARYING(255) NULL DEFAULT NULL COMMENT E'',
	PRIMARY KEY ("privilege_id")
);

-- Data exporting was unselected.
-- Dumping structure for table public.role
CREATE TABLE IF NOT EXISTS "role" (
	"role_id" BIGINT NOT NULL COMMENT E'',
	"description" CHARACTER VARYING(255) NULL DEFAULT NULL COMMENT E'',
	"name" CHARACTER VARYING(255) NULL DEFAULT NULL COMMENT E'',
	PRIMARY KEY ("role_id")
);

-- Data exporting was unselected.
-- Dumping structure for table public.role_privileges
CREATE TABLE IF NOT EXISTS "role_privileges" (
	"role_role_id" BIGINT NOT NULL COMMENT E'',
	"privileges_privilege_id" BIGINT NOT NULL COMMENT E'',
	PRIMARY KEY ("role_role_id","privileges_privilege_id")
);

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
