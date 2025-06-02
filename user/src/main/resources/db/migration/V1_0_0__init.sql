CREATE TABLE IF NOT EXISTS `users`
(
    `id`         int UNSIGNED     NOT NULL AUTO_INCREMENT,
    `username`   varchar(255)     NOT NULL,
    `password`   varchar(255)     NOT NULL,
    `nickname`   varchar(255)     NOT NULL,
    `gender`     tinyint UNSIGNED NOT NULL DEFAULT 0,
    `updated_at` datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `created_at` datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
);