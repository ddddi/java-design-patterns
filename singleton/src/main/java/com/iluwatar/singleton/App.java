/**
 * The MIT License
 * Copyright (c) 2014-2016 Ilkka Seppälä
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.iluwatar.singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Singleton pattern ensures that the class can have only one existing instance per Java classloader
 * instance and provides global access to it.
 * <p>
 * One of the risks of this pattern is that bugs resulting from setting a singleton up in a
 * distributed environment can be tricky to debug, since it will work fine if you debug with a
 * single classloader. Additionally, these problems can crop up a while after the implementation of
 * a singleton, since they may start out synchronous and only become async with time, so you it may
 * not be clear why you are seeing certain changes in behaviour.
 * <p>
 * There are many ways to implement the Singleton. The first one is the eagerly initialized instance
 * in {@link IvoryTower}. Eager initialization implies that the implementation is thread safe. If
 * you can afford giving up control of the instantiation moment, then this implementation will suit
 * you fine.
 * <p>
 * The other option to implement eagerly initialized Singleton is enum based Singleton. The example
 * is found in {@link EnumIvoryTower}. At first glance the code looks short and simple. However, you
 * should be aware of the downsides including committing to implementation strategy, extending the
 * enum class, serializability and restrictions to coding. These are extensively discussed in Stack
 * Overflow:
 * http://programmers.stackexchange.com/questions/179386/what-are-the-downsides-of-implementing
 * -a-singleton-with-javas-enum
 * <p>
 * {@link ThreadSafeLazyLoadedIvoryTower} is a Singleton implementation that is initialized on
 * demand. The downside is that it is very slow to access since the whole access method is
 * synchronized.
 * <p>
 * Another Singleton implementation that is initialized on demand is found in
 * {@link ThreadSafeDoubleCheckLocking}. It is somewhat faster than
 * {@link ThreadSafeLazyLoadedIvoryTower} since it doesn't synchronize the whole access method but
 * only the method internals on specific conditions.
 * <p>
 * Yet another way to implement thread safe lazily initialized Singleton can be found in
 * {@link InitializingOnDemandHolderIdiom}. However, this implementation requires at least Java 8
 * API level to work.
 */
public class App {

  private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

  /**
   *
   * 单例模式 确定只有 一个类仅有唯一的实例,并提供一个全局访问点
   * 实例: 在线计时器( 使用单例模式 可以避免在线人数统计问题 ) https://www.cnblogs.com/tangxiao1996/p/7899393.html
   *       数据连接
   * 特点:
   *    1. 单例类确保自己只有一个实例
   *    2. 单例类必须自己创建自己的实例
   *    3. 单例类必须为其他对象提供唯一的实例
   *
   * Program entry point.
   *  https://blog.csdn.net/hikobe8/article/details/79477853  ( 详细介绍 该方法中的所有创建单例模式的方法 )
   * @param args command line args
   */
  public static void main(String[] args) {

    // eagerly initialized singleton 单例模式直接创建
    // 声明一个最终静态的单例模式
      // 恶汉模式
    IvoryTower ivoryTower1 = IvoryTower.getInstance();
    IvoryTower ivoryTower2 = IvoryTower.getInstance();
    LOGGER.info("ivoryTower1={}", ivoryTower1);
    LOGGER.info("ivoryTower2={}", ivoryTower2);

    // lazily initialized singleton
      //  加了一个为空判断
      // 懒汉模式
    ThreadSafeLazyLoadedIvoryTower threadSafeIvoryTower1 =
        ThreadSafeLazyLoadedIvoryTower.getInstance();
    ThreadSafeLazyLoadedIvoryTower threadSafeIvoryTower2 =
        ThreadSafeLazyLoadedIvoryTower.getInstance();
    LOGGER.info("threadSafeIvoryTower1={}", threadSafeIvoryTower1);
    LOGGER.info("threadSafeIvoryTower2={}", threadSafeIvoryTower2);

    // enum singleton
      // 不太懂
    EnumIvoryTower enumIvoryTower1 = EnumIvoryTower.INSTANCE;
    EnumIvoryTower enumIvoryTower2 = EnumIvoryTower.INSTANCE;
    LOGGER.info("enumIvoryTower1={}", enumIvoryTower1);
    LOGGER.info("enumIvoryTower2={}", enumIvoryTower2);

    // double checked locking
      // 多线程单例 双重检查锁定  (在jdk1.5之前无效)
    ThreadSafeDoubleCheckLocking dcl1 = ThreadSafeDoubleCheckLocking.getInstance();
    LOGGER.info(dcl1.toString());
    ThreadSafeDoubleCheckLocking dcl2 = ThreadSafeDoubleCheckLocking.getInstance();
    LOGGER.info(dcl2.toString());

    // initialize on demand holder idiom
      // 静态内部类只会加载一次
    InitializingOnDemandHolderIdiom demandHolderIdiom =
        InitializingOnDemandHolderIdiom.getInstance();
    LOGGER.info(demandHolderIdiom.toString());
    InitializingOnDemandHolderIdiom demandHolderIdiom2 =
        InitializingOnDemandHolderIdiom.getInstance();
    LOGGER.info(demandHolderIdiom2.toString());
  }
}
