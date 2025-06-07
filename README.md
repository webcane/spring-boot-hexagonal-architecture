# Hexagonal Architecture With Spring Boot

![Gradle Build](https://github.com/arhohuttunen/spring-boot-hexagonal-architecture/workflows/Gradle%20Build/badge.svg)

This is the repository containing an example application expired by the blog post [Hexagonal Architecture With Spring Boot](https://www.arhohuttunen.com/hexagonal-architecture-spring-boot/).

## How to test
start application
```dotenv
./gradlew bootRun
```
open in browser
```dotenv
GET http://localhost:8080/receipt/757d9c0f-400f-4137-9aea-83e64ba3efb2
```

## spring modulith modules
```dotenv
# Adapter
> Logical name: adapter
> Base package: com.arhohuttunen.coffeeshop.adapter
> Excluded packages: 
  - com.arhohuttunen.coffeeshop.adapter.in.rest
  - com.arhohuttunen.coffeeshop.adapter.out.persistence
> Spring beans: none

# Architecture
> Logical name: architecture
> Base package: com.arhohuttunen.coffeeshop.architecture
> Excluded packages: none
> Spring beans: none

# Domain (open)
> Logical name: domain
> Base package: com.arhohuttunen.coffeeshop.domain
> Excluded packages: none
> Spring beans: none

# Port
> Logical name: port
> Base package: com.arhohuttunen.coffeeshop.port
> Excluded packages: none
> Named interfaces:
  + NamedInterface: name=<<UNNAMED>>, types=[]
  + NamedInterface: name=in, types=[ c.a.c.p.i.OrderingCoffee, c.a.c.p.i.PreparingCoffee ]
  + NamedInterface: name=out, types=[ c.a.c.p.o.OrderNotFound, c.a.c.p.o.Orders, c.a.c.p.o.Payments ]
> Spring beans: none

# Rest
> Logical name: adapter.in.rest
> Base package: com.arhohuttunen.coffeeshop.adapter.in.rest
> Excluded packages: none
> Spring beans:
  + ….OrderController
  + ….PaymentController
  + ….ReceiptController

# Persistence
> Logical name: adapter.out.persistence
> Base package: com.arhohuttunen.coffeeshop.adapter.out.persistence
> Excluded packages: none
> Spring beans:
  + ….OrderJpaRepository
  + ….OrdersJpaAdapter
  + ….PaymentJpaRepository
  + ….PaymentsJpaAdapter
  o ….usecase.TransactionalUseCaseAspect
  o ….usecase.TransactionalUseCaseExecutor
  o ….usecase.UseCaseTransactionConfiguration

# Usecases
> Logical name: usecases
> Base package: com.arhohuttunen.coffeeshop.usecases
> Excluded packages: none
> Spring beans: none
```
