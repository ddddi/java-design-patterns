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
package com.iluwatar.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *  代理模式: 利用开闭原则( 对扩展开放,对修改关闭 )
 *    使用代理对象来执行目标对象的方法并在代理对象中增强目标对象的一种设计模式
 *  代理模式元素:
 *      共同接口  代理对象  目标对象
 *  代理模式行为:
 *      由代理对象执行目标对象的方法、由代理对象扩展目标对象的方法
 *  代理模式的宏观特性:
 *      对客户端只暴露接口,不暴露它一下的架构
 *  代理模式的种类:
 *      静态代理、动态代理( jdk动态代理、cglib动态代理、Spring和AspectJ实现的动态代理 )
 *      ( 该方法就是静态代理 )
 *  jdk动态代理: 利用反射技术来实现 需要实现接口
 *     Proxy.newProxyInstance(类加载器,类实现的接口,句柄)
 *     在invoke前后添加通知,对原方法进行扩展
 *  cglib动态代理: 需要实现类
 * A proxy, in its most general form, is a class functioning as an interface to something else. The
 * proxy could interface to anything: a network connection, a large object in memory, a file, or
 * some other resource that is expensive or impossible to duplicate. In short, a proxy is a wrapper
 * or agent object that is being called by the client to access the real serving object behind the
 * scenes.
 * <p>
 * The Proxy design pattern allows you to provide an interface to other objects by creating a
 * wrapper class as the proxy. The wrapper class, which is the proxy, can add additional
 * functionality to the object of interest without changing the object's code.
 * <p>
 * In this example the proxy ({@link WizardTowerProxy}) controls access to the actual object (
 * {@link IvoryTower}).
 * 
 */
public class App {

  /**
   * Program entry point
   */
  public static void main(String[] args) {

      // 代理类中必须有有参构造方法 用于将目标类的对象赋值给该类
      // 在代理类中调用目标类的方法 以实现代理功能
    WizardTowerProxy proxy = new WizardTowerProxy(new IvoryTower());
    proxy.enter(new Wizard("Red wizard"));
    proxy.enter(new Wizard("White wizard"));
    proxy.enter(new Wizard("Black wizard"));
    proxy.enter(new Wizard("Green wizard"));
    proxy.enter(new Wizard("Brown wizard"));

    // 动态代理
      WizardTower proxyJDK = (WizardTower)Proxy.newProxyInstance(IvoryTower.class.getClassLoader(), IvoryTower.class.getInterfaces(), new InvocationHandler() {
          @Override
          public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
              System.out.println("jdk动态代理");
              method.invoke(new IvoryTower(), args);
              return null;
          }
      });
      proxyJDK.enter(new Wizard("Red wizard"));


  }
}
