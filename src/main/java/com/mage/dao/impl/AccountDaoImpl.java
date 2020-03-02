package com.mage.dao.impl;

import com.mage.dao.AccountDao;
import com.mage.vo.Account;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @param
 * @author qzp
 * @create 2020-02-21 22:55
 */
@Repository
@SuppressWarnings("all")
public class AccountDaoImpl implements AccountDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public Integer saveAccount(Account account) {
        String sql="insert into account(aname,type,money,remark,create_time,update_time,user_id) values(?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,account.getAname(),account.getType(),account.getMoney(),account.getRemarker(),account.getCreateTime(),account.getUpdateTime(),account.getUserId());
    }

    @Override
    public Integer saveAccountHasKey(Account account) {
        String sql="insert into account(aname,type,money,remark,create_time,update_time,user_id) values(?,?,?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1,account.getAname());
                preparedStatement.setInt(2,account.getType());
                preparedStatement.setObject(3,account.getMoney());
                preparedStatement.setString(4,account.getRemarker());
                preparedStatement.setObject(5,account.getCreateTime());
                preparedStatement.setObject(6,account.getUpdateTime());
                preparedStatement.setInt(7,account.getUserId());
                return preparedStatement;
            }
        },keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public Integer saveAccountBatch(List<Account> accounts) {
        String sql="insert into account(aname,type,money,remark,create_time,update_time,user_id) values(?,?,?,?,?,?,?)";
        return jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Account account = accounts.get(i);
                ps.setString(1,account.getAname());
                ps.setInt(2,account.getType());
                ps.setObject(3,account.getMoney());
                ps.setString(4,account.getRemarker());
                ps.setObject(5,account.getCreateTime());
                ps.setObject(6,account.getUpdateTime());
                ps.setInt(7,account.getUserId());
            }
            @Override
            public int getBatchSize() {
                return accounts.size();
            }
        }).length;
    }

    @Override
    public Integer countAccountsByUserId(Integer userId) {
        return jdbcTemplate.queryForObject("select count(*) from account where user_id=?",Integer.class,userId);
    }

    @Override
    public Account queryAccountById(Integer aid) {
        return jdbcTemplate.queryForObject("select id,aname,type,money,remark,create_time,update_time,user_id from account where id=?",
                new Object[]{aid}, new RowMapper<Account>() {
                    @Override
                    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Account account = new Account();
                        account.setUserId(rs.getInt("user_id"));
                        account.setType(rs.getInt("type"));
                        account.setMoney(BigDecimal.valueOf(rs.getDouble("money")));
                        account.setUpdateTime(rs.getDate("update_time"));
                        account.setCreateTime(rs.getDate("create_time"));
                        account.setAname(rs.getString("aname"));
                        account.setRemarker(rs.getString("remark"));
                        account.setId(rs.getInt("id"));
                        return account;
                    }
                });
    }

    @Override
    public List<Account> queryAccountsByParams(String aname, Integer type, Integer userId, String time) {
        List<Object> params = new ArrayList<>();
        StringBuffer stringBuffer = new StringBuffer("select id,aname,type,money,remark,create_time,update_time,user_id from account where user_id = ?");
        params.add(userId);
        if(StringUtils.isNotBlank(aname)){
            stringBuffer.append(" and aname like concat('%',?,'%')");
            params.add(aname);
        }
        if(null != type){
            stringBuffer.append(" and type =?");
            params.add(type);
        }
        if(StringUtils.isNotBlank(time)){
            stringBuffer.append(" and create_time >= ?");
            params.add(time);
        }
        return jdbcTemplate.query(stringBuffer.toString(), new RowMapper<Account>() {
            @Override
            public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
                Account account = new Account();
                account.setUserId(rs.getInt("user_id"));
                account.setType(rs.getInt("type"));
                account.setMoney(BigDecimal.valueOf(rs.getDouble("money")));
                account.setUpdateTime(rs.getDate("update_time"));
                account.setCreateTime(rs.getDate("create_time"));
                account.setAname(rs.getString("aname"));
                account.setRemarker(rs.getString("remark"));
                account.setId(rs.getInt("id"));
                return account;
            }
        },params.toArray());
    }

    @Override
    public List<Account> queryAccountsByParams(String aname, Integer type, Integer userId, String time, Integer pageNum, Integer pageSize) {
        List<Object> params = new ArrayList<>();
        StringBuffer stringBuffer = new StringBuffer("select id,aname,type,money,remark,create_time,update_time,user_id from account where user_id = ?");
        params.add(userId);
        if(StringUtils.isNotBlank(aname)){
            stringBuffer.append(" and aname like concat('%',?,'%')");
            params.add(aname);
        }
        if(null != type){
            stringBuffer.append(" and type =?");
            params.add(type);
        }
        if(StringUtils.isNotBlank(time)){
            stringBuffer.append(" and create_time >= ?");
            params.add(time);
        }

        int pm = (null==pageNum?1:pageNum);
        int pz = (null==pageSize?10:pageSize);
        stringBuffer.append(" limit ?,? ");
        params.add((pm-1)*pz);
        params.add(pz);
        return jdbcTemplate.query(stringBuffer.toString(), new RowMapper<Account>() {
            @Override
            public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
                Account account = new Account();
                account.setUserId(rs.getInt("user_id"));
                account.setType(rs.getInt("type"));
                account.setMoney(BigDecimal.valueOf(rs.getDouble("money")));
                account.setUpdateTime(rs.getDate("update_time"));
                account.setCreateTime(rs.getDate("create_time"));
                account.setAname(rs.getString("aname"));
                account.setRemarker(rs.getString("remark"));
                account.setId(rs.getInt("id"));
                return account;
            }
        },params.toArray());
    }

    @Override
    public Integer updateAccountById(Account account) {
        String sql= "update account set user_id=?,aname=?,type=?,money=?,create_time=?,remark=?,update_time=? where id = ?";
        return jdbcTemplate.update(sql,account.getUserId(),account.getAname(),account.getType(),account.getMoney(),account.getCreateTime(),account.getRemarker(),account.getUpdateTime(),account.getId());
    }

    @Override
    public Integer updateAccountsBach(List<Account> accounts) {
        String sql="update account set user_id=?,aname=?,type=?,money=?,create_time=?,remark=?,update_time=? where id=?";
        return jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Account account = accounts.get(i);
                ps.setInt(1,account.getUserId());
                ps.setString(2,account.getAname());
                ps.setInt(3,account.getType());
                ps.setObject(4,account.getMoney());
                ps.setObject(5,account.getCreateTime());
                ps.setString(6,account.getRemarker());
                ps.setObject(7,account.getUpdateTime());
                ps.setInt(8,account.getId());
            }
            @Override
            public int getBatchSize() {
                return accounts.size();
            }
        }).length;
    }

    @Override
    public Integer deleteAccountById(Integer aid) {
        return jdbcTemplate.update("delete from account where id=?", aid);
    }

    @Override
    public Integer deleteAccountsBach(Integer... aids) {
        String sql = "delete from account where id = ?";
        List<Integer> ids = Arrays.asList(aids);
        return jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1,ids.get(i));
            }

            @Override
            public int getBatchSize() {
                return ids.size();
            }
        }).length;
    }

    @Override
    public int accountPayOut(Integer sourceAid, BigDecimal amount) {
        return jdbcTemplate.update("update account set money = money - ? where id =?",amount,sourceAid);
    }

    @Override
    public int accountPayIn(Integer targetAid, BigDecimal amount) {
        return jdbcTemplate.update("update account set money  = money + ? where id =?",amount,targetAid);
    }
}
