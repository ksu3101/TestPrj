# Android architecture sample code

*개발 중..*

이 샘플 아키텍처 코드 내 에는 2개의 모듈(`mvvm-rx2`, `mvvm-coroutine`)이 존재 한다. 둘 다 본질적인 목적은 Redux 의 action 을 dispatch 하고 middleware 를 통해 action 을 핸들링 한 뒤 Reducer 를 통해 최종적으로 state 를 만들어 이를 ViewModel 에서 Data binding 된 View를 갱신하거나 다른 모듈 등 에서 state를 보고 다른 action 을 dispatch 하는 등 기본적인 Uni-Directional Data Flow(UDA) 를 선택 하였다. 

## MVVM 
비즈니스 코드와 뷰 코드를 분리 하여 각각 unit testing 을 진행 하게 한다.
실제 앱 에 적용 된다면 app 모듈 하나만이 아닌 여러가지 모듈을 두어 각각 테스팅 코드를구축 하고 의존을 정리 한다. 

### View
실제 화면에 보이는 레이아웃 xml. view 당 n개의 ViewModel 이 바인딩 될 수 있다. 

### ViewModel
View 와 바인딩 된 데이터를 업데이트 하고 관리 하는 역활. Model 을 통해서 전달 받은 State 를 이용 하여 화면을 rendering 한다. 

### Model 
실제 비즈니스 로직을 수행 하며 Redux 구조를 채택 하면서 MiddleWare들 과 Reducer 로 나뉘어져 있다. 
데이터의 흐름은 다음과 같다. 
> action -> middleWare -> reducer -> state

#### Action
사용자 혹은 어떠한 스트림에 의해 생성되어진 trigger 역활을 수행 하는 Action 이다. imuutable data class 로 되어 있으며 같은 유형의 경우 sealed 클래스로 묶어 공통적으로 처리 하게 할 수도 있다.

#### Middleware
dispatch 된 Action 을 이터레이셔닝 하면서 핸들링 한다. middleware 는 1개 일수도 그 보다 많을 수 있다. 모든 middleware 를 이터레이셔닝 모두 지나거나 혹은 중간에 기존의 Action 을 토대로 새로운 Action 이 될 수 있다. 이는 Success 에 대한 (Result)Action 혹은 Failed 에 대한 (Error)Action 이 될 수 있다. 

#### Reducer
Middleware 를 통해서 나온 Action 을 분기 하여 State를 만들어 준다. 

#### State
Reducer를 거쳐서 최종적으로 나온 immutable data class 이다. 이 또한 sealed 클래스로 묶을 수 있다. 만들어진 state 는 Store 인터페이스 구현체 에 마지막 상태가 저장되며 Store 를 주입 받을수 있다면 마지막 상태를 Store를 통해 받아 핸들링 할 수 있다. 

## Kotlin 
이 샘플 아키텍처 코드는 kotlin 100% 를 목표로 한다. 

### Koin 
Dependency Injection 툴 로 유명한 Dagger 가 있지만 JAVA 기반 이라 제네릭의 타입소거로 인해 inline-reified 와 같은 실체화된 타입을 적용 하여 반복되는 보일러 플레이트 코드를 제거 하기 위해서 선택. 
 - 장점 : 가독성이 높으며 Dagger 보다 러닝 커브가 매우 낮다. 어노테이션 방식이 아닌 kotlin 의 by keyword 방식이나 inject 와 같은 function 을 제공 하여 쉽게 사용 할 수 있다. 그리고 원하는 커스텀 scope 를 만들기도 쉬우며 Koin 인스턴스를 얻어 injection 하는 방법도 쉽다. 
 - 단점 : Activity scope 가 없어 custom scope 를 만들어야 한다. 이는 BaseActivity 와 같은 base 클래스에서 하는 방법이 있긴 하지만 state 를 알아야 injection 이 되기 때문에 다른 방법을 찾아야 한다. dagger 는 컴파일 시점에 각 모듈을 생성하지만 koin은 런타임 시 에 각 모듈을 필요 시점에 생성한다. 이 경우 런타임 예외가 발생할 확률이 높다. 
 - 꼭 Koin 이 아니더라도 DI 를 만드는 방법은 시간만 있으면 누구나 할 수 있을 거라고 생각된다. 이것은 나중에...
 
### 네트워크 라이브러리 (고민중)
 1. Retrofit + OkHttp
  - 장점 : 이미 사용성과 안정성이 인증된 라이브러리
  - 단점 : json 파서 와 Rx 나 코루틴으로 Result 를 파싱 하기 위한 adapter 가 필요 하다. 그리고 java로 되어 있다. 
 2. [Fuel](https://github.com/kittinunf/fuel) 
  - 장점 : kotlin 으로 구현된 네트워크 라이브러리 이다. 그리고 kotlin, java, coroutine, rx2 모두 지원 한다는 장점이 있다.
  - 단점 : 사용해본 적이 없어 안정성에 대해선 직접 사용 해서 확인 하는 수밖에 없을 거 같다. 
  
### 유닛 테스트
- View 테스트는 제외 한다. (시간부족 및 테스트 구현의 어려움 등..)
- Model(ActionProcessor 와 Reducer), ViewModel 에 대해서 테스트 코드를 작성 한다. 
- 필요에 따라 rest Api 에 대해 테스트 코드를 작성 한다. (이 때 결과 json파일을 만들어줘야 한다)
 
## Coroutine
기존 ReactiveX 를 대체 할 수 있는 도구로 생각 해왔었다. ReactiveX 는 매우 강력하고 좋은 툴 이지만 외부 라이브러리 이며 의존성이 너무 크다. 이전에 회사에서 일할 때 Rx1 에서 Rx2 로 고칠때 많은 시간을 필요로 했었던 경험이 있기 때문이다. 그에 반해 코루틴은 기본 코틀린과 같이 응용해서 사용 할 수 있으며 확장함수 등 으로 부족한 기능을 추가 할 수 있지 않을까 생각 하고 있다. 

