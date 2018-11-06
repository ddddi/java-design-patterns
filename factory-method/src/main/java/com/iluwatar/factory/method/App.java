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
package com.iluwatar.factory.method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  工厂方法模式: 即将 同一类型的调用类 继承同一父类  向上转为父类 在调用的模式
 *  使用接口和继承类 将继承类给到当前类中 并向上转型为 接口  然后调用继承类的方法
 *  转型:
 *      向上转型:
 *          父类引用指向子类对象, 但子类丢失除了与父类对象共有的其他方法
 *      好处: 代码整洁
 *      向下转型:
 *          子类对象的父类引用赋予子类,需强制转换
 *          里氏替换原则： “派生类（子类）对象能够替换其基类（超类）对象被使用。”
 *
 * The Factory Method is a creational design pattern which uses factory methods to deal with the
 * problem of creating objects without specifying the exact class of object that will be created.
 * This is done by creating objects via calling a factory method either specified in an interface
 * and implemented by child classes, or implemented in a base class and optionally overridden by
 * derived classes—rather than by calling a constructor.
 * <p>
 * In this Factory Method example we have an interface ({@link Blacksmith}) with a method for
 * creating objects ({@link Blacksmith#manufactureWeapon}). The concrete subclasses (
 * {@link OrcBlacksmith}, {@link ElfBlacksmith}) then override the method to produce objects of
 * their liking.
 * 
 */
public class App {

  private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

  private final Blacksmith blacksmith;
  
  /**
   * Creates an instance of <code>App</code> which will use <code>blacksmith</code> to manufacture 
   * the weapons for war.
   * <code>App</code> is unaware which concrete implementation of {@link Blacksmith} it is using.
   * The decision of which blacksmith implementation to use may depend on configuration, or
   * the type of rival in war.
   * @param blacksmith a non-null implementation of blacksmith
   */
  public App(Blacksmith blacksmith) {
    this.blacksmith = blacksmith;
  }
  
  /**
   * Program entry point
   * 
   * @param args command line args
   */
  public static void main(String[] args) {
    // Lets go to war with Orc weapons
    App app = new App(new OrcBlacksmith());
    app.manufactureWeapons();
    
    // Lets go to war with Elf weapons
    app = new App(new ElfBlacksmith());
    app.manufactureWeapons();
  }
  
  private void manufactureWeapons() {
    Weapon weapon;
    weapon = blacksmith.manufactureWeapon(WeaponType.SPEAR);
    LOGGER.info(weapon.toString());
    weapon = blacksmith.manufactureWeapon(WeaponType.AXE);
    LOGGER.info(weapon.toString());
  }
}
