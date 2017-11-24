package com.yibao.biggirl.model.music;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Author：Sid
 * Des：${音乐实体类,收藏 删除等}
 * Time:2017/9/3 14:31
 */
@Entity
public class MusicInfo
        implements Parcelable
{
    @Id(autoincrement = true)
    private Long   id;
    private String title;
    private String artist;
    private String album;
    private long   albumId;
    private String time;
    private String songUrl;

    public MusicInfo() {
    }

    protected MusicInfo(Parcel in) {
        title = in.readString();
        artist = in.readString();
        album = in.readString();
        albumId = in.readLong();
        time = in.readString();
        songUrl = in.readString();
    }

    @Generated(hash = 1887758998)
    public MusicInfo(Long id,
                     String title,
                     String artist,
                     String album,
                     long albumId,
                     String time,
                     String songUrl)
    {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.albumId = albumId;
        this.time = time;
        this.songUrl = songUrl;
    }

    public static final Creator<MusicInfo> CREATOR = new Creator<MusicInfo>() {
        @Override
        public MusicInfo createFromParcel(Parcel in) {
            return new MusicInfo(in);
        }

        @Override
        public MusicInfo[] newArray(int size) {
            return new MusicInfo[size];
        }
    };

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }


    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(artist);
        parcel.writeString(album);
        parcel.writeLong(albumId);
        parcel.writeString(time);
        parcel.writeString(songUrl);
    }

//    @Override
//    public int compareTo(@NonNull MusicInfo info) {
//        return title.compareTo(info.getTitle());
//    }
}
