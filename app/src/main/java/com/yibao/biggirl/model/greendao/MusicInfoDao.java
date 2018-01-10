package com.yibao.biggirl.model.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.yibao.biggirl.model.music.MusicInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "MUSIC_INFO".
*/
public class MusicInfoDao extends AbstractDao<MusicInfo, Long> {

    public static final String TABLENAME = "MUSIC_INFO";

    /**
     * Properties of entity MusicInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Title = new Property(1, String.class, "title", false, "TITLE");
        public final static Property Artist = new Property(2, String.class, "artist", false, "ARTIST");
        public final static Property Album = new Property(3, String.class, "album", false, "ALBUM");
        public final static Property AlbumId = new Property(4, long.class, "albumId", false, "ALBUM_ID");
        public final static Property Time = new Property(5, String.class, "time", false, "TIME");
        public final static Property SongUrl = new Property(6, String.class, "songUrl", false, "SONG_URL");
        public final static Property PlayStatus = new Property(7, int.class, "playStatus", false, "PLAY_STATUS");
    }


    public MusicInfoDao(DaoConfig config) {
        super(config);
    }
    
    public MusicInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"MUSIC_INFO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"TITLE\" TEXT," + // 1: title
                "\"ARTIST\" TEXT," + // 2: artist
                "\"ALBUM\" TEXT," + // 3: album
                "\"ALBUM_ID\" INTEGER NOT NULL ," + // 4: albumId
                "\"TIME\" TEXT," + // 5: time
                "\"SONG_URL\" TEXT," + // 6: songUrl
                "\"PLAY_STATUS\" INTEGER NOT NULL );"); // 7: playStatus
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"MUSIC_INFO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, MusicInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(2, title);
        }
 
        String artist = entity.getArtist();
        if (artist != null) {
            stmt.bindString(3, artist);
        }
 
        String album = entity.getAlbum();
        if (album != null) {
            stmt.bindString(4, album);
        }
        stmt.bindLong(5, entity.getAlbumId());
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(6, time);
        }
 
        String songUrl = entity.getSongUrl();
        if (songUrl != null) {
            stmt.bindString(7, songUrl);
        }
        stmt.bindLong(8, entity.getPlayStatus());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, MusicInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(2, title);
        }
 
        String artist = entity.getArtist();
        if (artist != null) {
            stmt.bindString(3, artist);
        }
 
        String album = entity.getAlbum();
        if (album != null) {
            stmt.bindString(4, album);
        }
        stmt.bindLong(5, entity.getAlbumId());
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(6, time);
        }
 
        String songUrl = entity.getSongUrl();
        if (songUrl != null) {
            stmt.bindString(7, songUrl);
        }
        stmt.bindLong(8, entity.getPlayStatus());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public MusicInfo readEntity(Cursor cursor, int offset) {
        MusicInfo entity = new MusicInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // title
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // artist
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // album
            cursor.getLong(offset + 4), // albumId
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // time
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // songUrl
            cursor.getInt(offset + 7) // playStatus
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, MusicInfo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setTitle(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setArtist(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setAlbum(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setAlbumId(cursor.getLong(offset + 4));
        entity.setTime(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setSongUrl(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setPlayStatus(cursor.getInt(offset + 7));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(MusicInfo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(MusicInfo entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(MusicInfo entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
