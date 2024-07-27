# E-n7777README

## 概览

这次我负责的模块是：

module_community（社区模块）

module_found（发现模块，包括一个分类和专题）

module_Hot（热门模块）

## 技术栈

采用MVVM架构

paging3 + flow实现下滑加载更多

paging的LoadStateAdapter处理加载状态

Retrofit + flow进行网络请求，用stateflow代替LiveData

Coroutine非阻塞式代码

Retrofit + Rxjava + LiveData网络请求

为vp设置自定义的PageTransformer

简单的属性动画

FloatingActionButton回到RecycleView顶部

SwipeRefreshLayout下拉刷新

## 详细介绍

### module_community

![community 00_00_00-00_00_30](https://github.com/QTwaiwai/OpenEye/blob/en_dev/E-n7777%E7%9A%84README/community%2000_00_00-00_00_30.gif?raw=true)

整体是一个rv每个item里面有一个vp，重写了一下PageTransformer达到一个可以像阅读器一样的翻页效果，每一个item点进去之后使用paging3 + flow实现下滑加载更多，网络出现问题会出现一个TextView点击重试，LoadStateViewHolder传入一个高阶函数，便于点击重试后，在adapter中做重试逻辑，用了一个悬浮按钮点击回到rv顶部，就是调用一下smoothScrollToPosition传入第一个位置0就ok了

### module_found

这个模块本来是打算做一个搜索的，但是开眼的搜索接口好像也是不能用(输入关键字之后什么也搜不到...)这个模块包含两个部分，分类和专题

![found_Classify 00_00_00-00_00_30](https://github.com/QTwaiwai/OpenEye/blob/en_dev/E-n7777%E7%9A%84README/found_Classify%2000_00_00-00_00_30.gif?raw=true)

分类就是rv，把layoutManager设成GridLayoutManager写了个渐变的shape当背景，点进去后用了一个协调者布局，折叠上去后将类别设置为Toolbar上的字，由于这个接口里的图片又双叒不能用，就在网上搜罗了一些跟专题比较对应的图片放在状态栏里，同样也是paging3加载更多，点击悬浮按钮回到顶部（这里要吐槽一下开眼的接口...真的...乱七八糟，找了好久，这里分类本来点击传的是id请求来的数据一直不照应，后面才发现应该传tagId

![found_Special 00_00_00-00_00_30](https://github.com/QTwaiwai/OpenEye/blob/en_dev/E-n7777%E7%9A%84README/found_Special%2000_00_00-00_00_30.gif?raw=true)

专题...接口也是一言难尽，预览的那个接口图片不能用，但是点进去之后的图片是可以看的，用了一个比较呆的方法，先拿到预览的每个item的id，直接再用另一个接口请求来详情的数据，拿到图片放在All的界面上，导致很卡

跳转详情页使用了属性动画，分类那边也用了但是是设在TextView上就不太明显

### module_Hot

![Hot 00_00_00-00_00_30](https://github.com/QTwaiwai/OpenEye/blob/en_dev/E-n7777%E7%9A%84README/Hot%2000_00_00-00_00_30.gif?raw=true)

这个模块网络请求用的是RxJava + LiveData，使用了vp2 + fragment同样也是写了一下PageTransformer用来切换两个页面(别问为什么没有周排行，因为接口图片太多不能看了，甚至官方的APP周排行能看的图片都没几个)，这里的Item做了个分享的功能，点击图标启动分享选择器Intent.createChooser()

## 不足

对知识的使用不够灵活，导致很多代码都写的挺冗杂的，协程学了但是感觉理解的还是不透彻，项目没有很多有新意的地方

## 心得体会

学安卓断断续续也差不多有一年了，确实学到了很多东西，学期中间总会有一些杂七杂八的事情，很多东西之前看过但是上手写项目的时候发现真正用起来还是跟看的时候不太一样，有挺多东西也都没学，自定义View，事件分发，嵌套滑动等等，处理内存泄露的意识也不够，学过的东西理解不到位用起来也不灵活，这次考核跟之前不一样的是进行了多人开发，刚开始的时候真的很恼火，因为之前下载了个不稳定版本的AS，出现了很多问题，很感谢我的partner帮了我们这个团队很多，这次暑假考核大家是集中在一起写的，收获了很多，不止是知识方面，还收获了其他小登们的友谊。

也很感谢学长们对我们的教导，感谢伙伴们的支持，总的来说在红岩学习的这一年真的很难忘，不管多久回想起来都会是独特的一年