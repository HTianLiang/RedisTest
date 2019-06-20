package soft.TL.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/*
    Jedis的测试
 */
public class JedisDemos {
    @Test
    public void test(){
        //1、设置IP地址和端口
        Jedis jedis = new Jedis("192.168.199.128",6379);
        //2、保存数据
        jedis.set("num","1234");
        //3、获取数据
        String value = jedis.get("name");

        System.out.println(value);
        //4、释放资源
        jedis.close();
    }
    @Test
    /*
        连接池方式连接
     */
    public void test1(){
        //获得连接池的配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        //设置最大连接数
        config.setMaxTotal(30);
        //设置最大空闲连接数
        config.setMaxIdle(10);

        //获得链接池
        JedisPool jedisPool = new JedisPool(config,"192.168.199.128",6379);

        //获得核心对象
        Jedis jedis = null;
        try {
            //通过连接池来获得链接
            jedis = jedisPool.getResource();
            //设置数据
            jedis.set("name","李四");
            //获取数据
            String name = jedis.get("name");
            System.out.println(name);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //释放资源
            if (jedis != null){
                jedis.close();
            }
            if (jedisPool != null){
                jedisPool.close();
            }
        }
    }

}
