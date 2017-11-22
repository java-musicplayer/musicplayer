PRAGMA foreign_keys=ON;
BEGIN TRANSACTION;
CREATE TABLE "music" (
    "id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "md5value" TEXT NOT NULL DEFAULT (''),
    "name" TEXT DEFAULT (''),
    "singer" TEXT DEFAULT (''),
    "musicUrl" TEXT NOT NULL DEFAULT ('')
);
INSERT INTO "music" VALUES(1,'11111','222','ss');
CREATE TABLE "musicsheet" (
    "id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "uuid" TEXT NOT NULL DEFAULT (''),
    "name" TEXT DEFAULT (''),
    "creatorId" TEXT DEFAULT (''),
    "dateCreated" TEXT DEFAULT (''),
    "pictureUrl" TEXT DEFAULT ('')
);
CREATE TABLE "musicsheet_music" (
    "id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "musicId" INTEGER NOT NULL REFERENCES "music"(id) ON UPDATE CASCADE ON DELETE CASCADE,
    "musicsheetId" INTEGER NOT NULL REFERENCES "musicsheet"(id) ON UPDATE CASCADE ON DELETE CASCADE
);
DELETE FROM sqlite_sequence;
INSERT INTO "sqlite_sequence" VALUES('music',1);
COMMIT;
