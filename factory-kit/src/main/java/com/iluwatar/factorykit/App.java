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
package com.iluwatar.factorykit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

/**
 * java.util.function: 主要用于实现java8最大的特性函数式编程
 * Function类:
 *
 * Consumer类:
 *      通过传递一个参数对参数进行操作
 *      一个accept方法用来对输入的参数进行自定义操作,需要实例化对象的时候进行Override
 *  Predicate类:
 *      test 方法,是一个抽象方法,需要编程者自己去Override
 *      and 方法 返回这两个对象分别调用test方法之后的到的布尔值相当于predicate1.test() && predicate2.test()
 *      or  方法 返回这两个对象分别调用test方法之后的到的布尔值相当于predicate1.test() || predicate2.test()
 *  Supplier类:
 *      用于提供对象,只有一个get方法,需要编译自定义
 *  直接new 接口 相当于直接new了一个实现该接口的匿名内部类
 * Factory-kit is a creational pattern which defines a factory of immutable content
 * with separated builder and factory interfaces to deal with the problem of
 * creating one of the objects specified directly in the factory-kit instance.
 *
 * <p>
 * In the given example {@link WeaponFactory} represents the factory-kit, that contains
 * four {@link Builder}s for creating new objects of
 * the classes implementing {@link Weapon} interface.
 * <br>Each of them can be called with {@link WeaponFactory#create(WeaponType)} method, with
 * an input representing an instance of {@link WeaponType} that needs to
 * be mapped explicitly with desired class type in the factory instance.
 */
public class App {

  private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

  /**
   * Program entry point.
   *
   * @param args command line args
   */
  public static void main(String[] args) {
    WeaponFactory factory = WeaponFactory.factory(builder -> {
      builder.add(WeaponType.SWORD, Sword::new);
      builder.add(WeaponType.AXE, Axe::new);
      builder.add(WeaponType.SPEAR, Spear::new);
      builder.add(WeaponType.BOW, Bow::new);
    });
    Weapon axe = factory.create(WeaponType.AXE);
    LOGGER.info(axe.toString());
  }
}
