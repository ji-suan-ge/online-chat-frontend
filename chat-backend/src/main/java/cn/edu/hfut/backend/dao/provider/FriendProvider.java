package cn.edu.hfut.backend.dao.provider;

import org.apache.ibatis.jdbc.SQL;

public class FriendProvider {
    public String findFriend(Integer friendId, String nickname, String account) {
        return new SQL() {{
            SELECT("*");
            FROM("user");
            if (friendId != null) {
                WHERE("ID = #{friendId}");
            } else if (nickname != null) {
                WHERE("nickname = #{nickname}");
            } else if (account != null) {
                WHERE("account = #{account}");
            } else {
                WHERE("1 = 9");
            }
        }}.toString();
    }
}

