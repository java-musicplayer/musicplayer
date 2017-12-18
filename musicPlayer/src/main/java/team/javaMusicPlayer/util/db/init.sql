PRAGMA foreign_keys = ON;
BEGIN TRANSACTION;
CREATE TABLE "music" (
    "id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "md5value" TEXT NOT NULL DEFAULT (''),
    "name" TEXT DEFAULT (''),
    "singer" TEXT DEFAULT (''),
    "musicUrl" TEXT NOT NULL DEFAULT ('')
);

CREATE TABLE "musicsheet" (
    "id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "uuid" TEXT NOT NULL DEFAULT (''),
    "name" TEXT DEFAULT (''),
    "creatorId" TEXT DEFAULT (''),
    "creator" TEXT DEFAULT (''),
    "dateCreated" TEXT DEFAULT (''),
    "pictureUrl" TEXT DEFAULT ('')
);
CREATE TABLE "musicsheet_music" (
    "id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "musicId" INTEGER NOT NULL,
    "musicsheetId" INTEGER NOT NULL,
    FOREIGN KEY(musicId) REFERENCES "music"(id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(musicsheetId) REFERENCES "musicsheet"(id) ON UPDATE CASCADE ON DELETE CASCADE
);
DELETE FROM sqlite_sequence;
COMMIT;
