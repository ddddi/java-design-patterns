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
package com.iluwatar.observer;

import com.iluwatar.observer.generic.GHobbits;
import com.iluwatar.observer.generic.GOrcs;
import com.iluwatar.observer.generic.GWeather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  观察者模式:( 发布订阅模式 )
 *      定义: 在对象之间定义了一对多的依赖,当一个对象改变状态,依赖它的对象会收到通知并自动更新
 *            通过接口对观察者与被观察者进行逻辑解耦合.
 *
 *       四个角色:
 *            抽象被观察者角色:
 *            抽象观察者角色:
 *            具体被观察者角色:
 *            具体观察者角色:
 *       使用场景:
 *            微博订阅
 *            微信公总号服务,不定时发布一些消息,关注公总号就可以推送消息,不关注就不推送消息
 *       关键点:
 *            1. 针对观察者与被观察者分别定义接口,有利于分别进行扩展
 *            2. 重点就在被观察者的实现中:
 *              1> 定义观察者集合,并定义针对集合的添加、删除操作,用于增加、删除订阅者(观察者)
 *              2> 定义通知方法,用于将新情况通知给观察者用于(订阅者用户)
 *              3> 观察者中需要有个接受被观察者通知的方法
 *
 *
 *
 *  为了解耦和的
 *
 * The Observer pattern is a software design pattern in which an object, called the subject,
 * maintains a list of its dependents, called observers, and notifies them automatically of any
 * state changes, usually by calling one of their methods. It is mainly used to implement
 * distributed event handling systems. The Observer pattern is also a key part in the familiar
 * model–view–controller (MVC) architectural pattern. The Observer pattern is implemented in
 * numerous programming libraries and systems, including almost all GUI toolkits.
 * <p>
 * In this example {@link Weather} has a state that can be observed. The {@link Orcs} and
 * {@link Hobbits} register as observers and receive notifications when the {@link Weather} changes.
 * 
 */
public class App {

  private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

  /**
   * Program entry point
   * 
   * @param args command line args
   */
  public static void main(String[] args) {

    Weather weather = new Weather();
    weather.addObserver(new Orcs());
    weather.addObserver(new Hobbits());

    weather.timePasses();
    weather.timePasses();
    weather.timePasses();
    weather.timePasses();

    // Generic observer inspired by Java Generics and Collection by Naftalin & Wadler
    LOGGER.info("--Running generic version--");
    GWeather gWeather = new GWeather();
    gWeather.addObserver(new GOrcs());
    gWeather.addObserver(new GHobbits());

    gWeather.timePasses();
    gWeather.timePasses();
    gWeather.timePasses();
    gWeather.timePasses();
  }
}
