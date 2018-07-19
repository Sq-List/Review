### 1.缓存注解
1. @Cacheable
    @Cacheable 的作用：
    主要针对方法配置，能够根据方法的请求参数对其结果进行缓存
    | 参数 | 解释 | example |
    | --- | --- | --- |
    | value | 缓存的名称，在 spring 配置文件中定义，必须指定至少一个 | @Cacheable(value=”mycache”)<br>@Cacheable(value={”cache1”,”cache2”}) |
    | key | 缓存的 key，可以为空，如果指定要按照 SpEL 表达式编写，如果不指定，则缺省按照方法的所有参数进行组合 | @Cacheable(value=”testcache”,key=”#userName”) |
    | condition | 缓存的条件，可以为空，使用 SpEL 编写，返回 true 或者 false，只有为 true 才进行缓存 | @Cacheable(value=”testcache”,condition=”#userName.length()>2”) |

1. @CachePut
    @CachePut 的作用：
    主要针对方法配置，能够根据方法的请求参数对其结果进行缓存，和 @Cacheable 不同的是，它 **每次都会触发真实方法的调用**
    | 参数 | 解释 | example |
    | --- | --- | --- |
    | value | 缓存的名称，在 spring 配置文件中定义，必须指定至少一个 | @CachePut(value=”my cache”) |
    | key | 缓存的 key，可以为空，如果指定要按照 SpEL 表达式编写，如果不指定，则缺省按照方法的所有参数进行组合 | @CachePut(value=”testcache”,key=”#userName”) |
    | condition | 缓存的条件，可以为空，使用 SpEL 编写，返回 true 或者 false，只有为 true 才进行缓存 | 	@CachePut(value=”testcache”,condition=”#userName.length()>2”) |

1. @CacheEvict
    @CacheEvict 的作用：
    主要针对方法配置，能够根据一定的条件对缓存进行清空
    | 参数 | 解释 | example |
    | --- | --- | --- |
    | value | 缓存的名称，在 spring 配置文件中定义，必须指定至少一个 | @CachePut(value=”my cache”) |
    | key | 缓存的 key，可以为空，如果指定要按照 SpEL 表达式编写，如果不指定，则缺省按照方法的所有参数进行组合 | @CachePut(value=”testcache”,key=”#userName”) |
    | condition | 缓存的条件，可以为空，使用 SpEL 编写，返回 true 或者 false，只有为 true 才进行缓存 | 	@CachePut(value=”testcache”,condition=”#userName.length()>2”) |
    | allEntries | 是否清空所有缓存内容，缺省为 false，如果指定为 true，则 **方法调用后将立即清空所有缓存** | @CachEvict(value=”testcache”,allEntries=true) |
    | beforeInvocation | **是否在方法执行前就清空**，缺省为 false，如果指定为 true，则在方法还没有执行的时候就清空缓存，缺省情况下，如果方法执行抛出异常，则不会清空缓存 | @CachEvict(value=”testcache”，beforeInvocation=true) |

1. @CacheConfig
    所有的@Cacheable()里面都有一个value＝“xxx”的属性，这显然如果方法多了，写起来也是挺累的，如果可以一次性声明完 那就省事了， 
    所以，有了@CacheConfig这个配置，@CacheConfig is a class-level annotation that allows to share the cache names，如果你在你的方法写别的名字，那么依然以方法的名字为准。
    ```java
    @CacheConfig("books")
    public class BookRepositoryImpl implements BookRepository {

        @Cacheable
        public Book findBook(ISBN isbn) {...}
    }
    ```

1. @Caching
    有时候我们可能组合多个Cache注解使用；比如用户新增成功后，我们要添加id–>user；username—>user；email—>user的缓存；此时就需要@Caching组合多个注解标签了。
    ```java
    @Caching(put = {
        @CachePut(value = "user", key = "#user.id"),
        @CachePut(value = "user", key = "#user.username"),
        @CachePut(value = "user", key = "#user.email")
    })
    public User save(User user)
    ```
