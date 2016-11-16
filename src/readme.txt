mappedby
1、只有OneToOne，OneToMany，ManyToMany上才有mappedBy属性，ManyToOne不存在该属性；
2、mappedBy是定义在被拥有方的，不负责维护级联关系，他指向拥有方（即关系的维护者）
3、mappedBy跟joinColumn/JoinTable总是处于互斥的一方,比如：Address2与Department2之间的关系，many2many中Main.main()

todo
fetch=FetchType.Lazy  -- 不生效


扩展：
Hibernate的悲观锁和乐观锁 -- 可以参考version.Main中乐观锁使用场景
悲观锁：别人读取到数据后，其他人无法读取
乐观锁：都可以读取，但不能针对同一条原记录同时修改
