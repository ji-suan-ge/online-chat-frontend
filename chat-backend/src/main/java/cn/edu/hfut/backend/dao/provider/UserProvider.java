package cn.edu.hfut.backend.dao.provider;

import org.apache.ibatis.jdbc.SQL;

import java.sql.Timestamp;

public class UserProvider {
    public String updateById(Integer id, String nickname, Integer gender, Timestamp birthday) {
        return new SQL() {{
            UPDATE("user");
            if (nickname != null) {
                SET("nickname = #{nickname}");
            }
            if (gender != null) {
                SET("gender = #{gender}");
            }
            if (birthday != null) {
                SET("birthday = #{birthday}");
            }
            WHERE("id = #{id}");
        }}.toString();
    }
}
