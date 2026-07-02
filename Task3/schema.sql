```
Создание:
create table product (
	id serial unique not null,
	name char(20) not null,
	cost integer check(cost > -1)
);
```

```
Добавим продукт:
insert into product (name, cost) values ('Chocolate', 90);
```

```
Получаем:
SELECT * FROM public.product
```