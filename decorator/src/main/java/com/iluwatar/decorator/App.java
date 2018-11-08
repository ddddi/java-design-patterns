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
package com.iluwatar.decorator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  装饰器模式:
 *      动态的为一个对象增加新的功能
 *      用于替换继承的技术,无需通过继承增加子类就能扩展对象的新功能.使用对象的关联模式来代替继承关系,更加灵活,同时避免类型体系快速膨胀
 *      所需内容:
 *          统一接口
 *          实现类:被装饰器类
 *          装饰类: 实现统一接口的同时,内部维护一个实现类,并且可以通过构造函数初始化
 *      开发中的场景:
 *          IO中输入输出流的设计
 *          Swing包中图形界面构建功能
 *          Servlet API 中提供了一个request对象的Decorator设计模式的默认实现类HttpServletRequestWrapper,
 *          HttpServletRequestWrapper类增强了request对象的功能
 *          Struts2中,request response session对象的处理
 *      装饰器模式和代理模式的区别:
 *           通常在编译时就已经确定了，而装饰者能够在运行时递归地被构造
 *           代理模式不能被多层嵌套
 *           根本是两者的目的不同
 *
 *
 *
 *
 * The Decorator pattern is a more flexible alternative to subclassing. The Decorator class
 * implements the same interface as the target and uses aggregation to "decorate" calls to the
 * target. Using the Decorator pattern it is possible to change the behavior of the class during
 * runtime.
 * <p>
 * In this example we show how the simple {@link SimpleTroll} first attacks and then flees the battle.
 * Then we decorate the {@link SimpleTroll} with a {@link ClubbedTroll} and perform the attack again. You
 * can see how the behavior changes after the decoration.
 * 
 */
public class App {

  private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

  /**
   *
   * Program entry point
   * 
   * @param args command line args
   */
  public static void main(String[] args) {

    // simple troll
    LOGGER.info("A simple looking troll approaches.");
    Troll troll = new SimpleTroll();
    troll.attack();
    troll.fleeBattle();
    LOGGER.info("Simple troll power {}.\n", troll.getAttackPower());

    // change the behavior of the simple troll by adding a decorator
    LOGGER.info("A troll with huge club surprises you.");
    Troll clubbedTroll = new ClubbedTroll(troll);
    clubbedTroll.attack();
    clubbedTroll.fleeBattle();
    LOGGER.info("Clubbed troll power {}.\n", clubbedTroll.getAttackPower());
  }
}
