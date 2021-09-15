"""
DSC 20 Final Project
Name: James Lu
PID:  A16687580
"""
from util import Stack, Queue
from datetime import datetime


def doctest():
    """
    ------------------------ PRODUCT TEST ------------------------

    >>> p1 = Product("iphone",399)
    >>> p2 = Special_Product("macbook air",999)
    >>> p3 = Limited_Product("free iphone", 0, 10)
    >>> p1, p2, p3
    (PRODUCT <0>, PRODUCT <1>, PRODUCT <2>)
    >>> print(p1)
    <0> iphone - 399$
    >>> print(p2)
    <1> macbook air - 999$ (special)
    >>> print(p3)
    <2> free iphone - 0$ (10 left)

    ------------------------ WAREHOUSE TEST ------------------------

    >>> wh = Warehouse()
    >>> st = Store(wh)
    >>> wh.import_product(p1)
    >>> wh.import_product(p2)
    >>> wh.import_product(p3)
    >>> print(wh)
    Warehouse with 3 products
    >>> print(wh.get_product(1))
    <1> macbook air - 999$ (special)
    >>> st.view_products()
    ============================
    <ID> Product - Price
    <0> iphone - 399$
    <1> macbook air - 999$ (special)
    <2> free iphone - 0$ (10 left)
    ============================
    >>> wh.export_product(3)
    >>> wh.export_product(2)
    PRODUCT <2>
    >>> wh.remove_product(0)
    True
    >>> st.view_products()
    ============================
    <ID> Product - Price
    <1> macbook air - 999$ (special)
    <2> free iphone - 0$ (9 left)
    ============================
    >>> st.view_products(sort = True)
    ============================
    <ID> Product - Price
    <2> free iphone - 0$ (9 left)
    <1> macbook air - 999$ (special)
    ============================
    >>> wh.remove_product(0)
    False
    >>> [wh.export_product(2) for i in range(9)]
    [PRODUCT <2>, PRODUCT <2>, PRODUCT <2>, PRODUCT <2>, PRODUCT <2>,\
 PRODUCT <2>, PRODUCT <2>, PRODUCT <2>, PRODUCT <2>]
    >>> st.view_products()
    ============================
    <ID> Product - Price
    <1> macbook air - 999$ (special)
    ============================
    >>> wh.show_log()
    Product <0> imported - 2020-11-26 07:09:17.709522
    Product <1> imported - 2020-11-26 07:09:17.709584
    Product <2> imported - 2020-11-26 07:09:17.709612
    Product <2> exported - 2020-11-26 07:09:17.709745
    Product <0> removed  - 2020-11-26 07:09:17.709776
    Product <2> exported - 2020-11-26 07:09:17.709886
    Product <2> exported - 2020-11-26 07:09:17.709893
    Product <2> exported - 2020-11-26 07:09:17.709897
    Product <2> exported - 2020-11-26 07:09:17.709901
    Product <2> exported - 2020-11-26 07:09:17.709905
    Product <2> exported - 2020-11-26 07:09:17.709909
    Product <2> exported - 2020-11-26 07:09:17.709913
    Product <2> exported - 2020-11-26 07:09:17.709916
    Product <2> exported - 2020-11-26 07:09:17.709920
    Product <2> removed  - 2020-11-26 07:09:17.709924

    ------------------------ USER TEST ------------------------

    >>> u1 = User( 'Jerry', st)
    >>> u2 = Premium_User( 'FYX', st)
    >>> u1
    USER<0>
    >>> u2
    USER<1>
    >>> print(u1)
    standard user: Jerry - 0$
    >>> u2.add_balance(2000)
    >>> print(u2)
    premium user: FYX - 2000$
    
    >>> wh.import_product(p1)
    >>> u1 = User("A",st)
    >>> u1.add_cart(0)
    >>> u1.add_cart(0)
    >>> u1.view_cart()
    (front) <0> iphone - 399$ -- <0> iphone - 399$ (rear)
    >>> u1.checkout()
    STORE: Not enough money QQ
    []
    >>> u1.add_balance(1000)
    >>> u1.checkout()
    STORE: A ordered iphone. A has 562$ left.
    STORE: A ordered iphone. A has 124$ left.
    [PRODUCT <0>, PRODUCT <0>]
    >>> p4 = Limited_Product("Ipad", 600, 2)
    >>> wh.import_product(p4)
    >>> u2.buy_all(3)
    STORE: FYX ordered Ipad. FYX has 1400$ left.
    STORE: FYX ordered Ipad. FYX has 800$ left.
    STORE: FYX ordered Ipad. FYX has 200$ left.
    STORE: Not enough money QQ
    [PRODUCT <3>, PRODUCT <3>, PRODUCT <3>]

    ------------------- HISTORY / UNDO TEST -------------------

    >>> u1.view_history()
    (bottom) <0> 2 bought <0> iphone - 399$ at 2020-12-03 21:27:54.903632 -- \
<1> 2 bought <0> iphone - 399$ at 2020-12-03 21:27:54.903658 (top)
    >>> u2.add_balance(2000)
    >>> u2.balance
    2200
    >>> u2.add_cart(0)
    >>> u2.add_cart(0)
    >>> u2.checkout()
    STORE: FYX ordered iphone. FYX has 1801$ left.
    STORE: FYX ordered iphone. FYX has 1402$ left.
    [PRODUCT <0>, PRODUCT <0>]
    >>> u2.view_history()
    (bottom) <2> 1 bought <3> Ipad - 600$ (2 left) at \
2021-03-15 15:56:53.223549 -- <3> 1 bought <3> Ipad - 600$ (2 left) at \
2021-03-15 15:56:53.223594 -- <4> 1 bought <3> Ipad - 600$ (2 left) at \
2021-03-15 15:56:53.223632 -- <5> 1 bought <0> iphone - 399$ at 2021-03-15 \
15:56:53.227443 -- <6> 1 bought <0> iphone - 399$ at 2021-03-15 \
15:56:53.227491 (top)
    >>> u1.undo_purchase()
    STORE: A refunded iphone. A has 523$ left.
    >>> u2.undo_all()
    STORE: FYX refunded iphone. FYX has 1801$ left.
    STORE: FYX refunded iphone. FYX has 2200$ left.

    -------------------------- EC TEST ------------------------
    >>> p1 = Product("A",20)
    >>> p2 = Special_Product("B",7)
    >>> p3 = Limited_Product("C", 1, 2)
    >>> wh = Warehouse()
    >>> wh.import_product(p1)
    >>> wh.import_product(p2)
    >>> wh.import_product(p3)
    >>> st = Store(wh)
    >>> st.view_products()
    ============================
    <ID> Product - Price
    <4> A - 20$
    <5> B - 7$ (special)
    <6> C - 1$ (2 left)
    ============================
    >>> st.so_rich(45)
    1
    >>> st.so_rich(61)
    0
    >>> st.so_rich_recursive(45)
    1
    >>> st.so_rich_recursive(61)
    0
    """
    pass


#######################################################################
#                               PRODUCT                               #
#######################################################################
class Product:
    """
    This class is an abstraction for different products available in this
    system.

    Class Attributes
    ---------------------------------------
    product_counter : int
        tracks total number of products

    Object Attributes
    ---------------------------------------
    name : string
        name of product
    price : int
        price of product
    id : int
        id of product
    """

    ##### Part 1.1 #####
    product_counter = 0

    def __init__(self, name, price):
        """
        Constructor of Product

        Params
        ---------------------------------------
        name : string
            name of product
        price : int
            price of product
        """
        self.name = name
        self.price = price
        self.id = Product.product_counter
        Product.product_counter += 1

    def __str__(self):
        """
        String representation of Product

        Return
        ---------------------------------------
        product : string
            Returns string representation of Product

        ex: “<{id}> {name} - {price}$”
        """
        return '<{}> {} - {}$'.format(self.id, self.name, self.price)

    def __repr__(self):
        """
        Object representation of Product

        Return
        ---------------------------------------
        product : string
            Returns object representation of Product

        ex: “PRODUCT <{id}>”
        """
        return 'PRODUCT <{}>'.format(self.id)


class Limited_Product(Product):
    """
    This subclass is an implementation of limited products available in this
    system.

    Object Attributes
    ---------------------------------------
    name : string
        name of product
    price : int
        price of product
    id : int
        id of product
    amount : int
        number of available products left
    """

    ##### Part 1.2 #####
    def __init__(self, name, price, amount):
        """
        Constructor of Limited_Product

        Params
        ---------------------------------------
        name : string
            name of product
        price : int
            price of product
        amount : int
            amount of limited product
        """
        super().__init__(name, price)
        self.amount = amount

    def __str__(self):
        """
        String representation of Limited_Product

        Return
        ---------------------------------------
        product : string
            Returns string representation of Limited_Product

        ex: “<{id}> {name} - {price}$ ({amount} left)”
        """
        return '<{}> {} - {}$ ({} left)'.format(self.id, self.name, self.price,
                                                self.amount)


class Special_Product(Product):
    """
    This subclass is an implementation of special products available in this
    system.

    Object Attributes
    ---------------------------------------
    name : string
        name of product
    price : int
        price of product
    id : int
        id of product
    description : string
        description of item, default='special'
    """

    ##### Part 1.3 #####
    def __init__(self, name, price, description='special'):
        """
        Constructor of Special_Product

        Params
        ---------------------------------------
        name : string
            name of product
        price : int
            price of product
        description : string
            description of special product
        """
        super().__init__(name, price)
        self.description = description

    def __str__(self):
        """
        String representation of Special_Product

        Return
        ---------------------------------------
        product : string
            Returns string representation of Special_Product

        ex: “<{id}> {name} - {price}$ ({description})”
        """
        return '<{}> {} - {}$ ({})'.format(self.id, self.name, self.price,
                                           self.description)


#######################################################################
#                              WAREHOUSE                              #
#######################################################################


class Warehouse:
    """
    This class is an implementation of a warehouse that stores the
    products available in this system.

    Object Attributes
    ---------------------------------------
    name : string
        name of product
    price : int
        price of product
    id : int
        id of product

    Methods
    ---------------------------------------
    get_product(product_id)
        Return the product instance with the given id (int) from the
        inventory. If the product is not found, return None instead.
    list_products()
        Return a list of all actual product instances stored in the inventory.
    remove_product(product_id)
        Remove the product instance with the given id (int) from the inventory.
        Return True if it’s successfully removed, and return False if the
        product is already not in the inventory. It will also append:
        “Product <{product id}> removed  - {time}”
        to the log if the product is successfully removed.
    import_product(product)
        Import the product instance to the inventory. Appends:
        “Product <{product id}> imported - {time}”
        to the log after importing the product.
    export_product(product_id)
        Export the product instance with the given id from the inventory.
        Return None if the specified product does not exist in the inventory.
    size()
        Return the number of products stored in the inventory.
    show_log (self)
        Print all log strings in the log. Each log string should occupy
        exactly one line.
    """

    ##### Part 2 #####
    def __init__(self):
        """
        Constructor of Warehouse
        """
        self.inventory = {}
        self.log = []

    def __str__(self):
        """
        String representation of Warehouse

        Return
        ---------------------------------------
        warehouse : string
            Returns string representation of Warehouse

        ex: “Warehouse with {number of products stored} products”
        """
        return 'Warehouse with {} products'.format(len(self.inventory.values()))

    def get_product(self, product_id):
        """
        Gets product object from warehouse

        Params
        ---------------------------------------
        product_id : int
            id of product to get

        Return
        ---------------------------------------
        product : Product
            Returns product object or None if product is not found
        """
        return self.inventory.get(product_id)

    def list_products(self):
        """
        Gets list of product objects from warehouse

        Return
        ---------------------------------------
        products : list
            Returns list of product objects
        """
        return list(self.inventory.values())

    def remove_product(self, product_id):
        """
        Removes product object from warehouse, appends removal to log

        Params
        ---------------------------------------
        product_id : int
            id of product to remove

        Return
        ---------------------------------------
        remove : boolean
            Returns True if removed, False if not
        """
        if product_id in self.inventory.keys():
            self.log.append(
                'Product <{}> removed  - {}'.format(product_id, datetime.now()))
            self.inventory.pop(product_id)
            return True
        else:
            return False

    def import_product(self, product):
        """
        Imports product object to warehouse if not already in it,
        appends addition to log

        Params
        ---------------------------------------
        product : Product
            Product to import
        """
        if product not in self.inventory.values():
            self.log.append(
                'Product <{}> imported - {}'.format(product.id, datetime.now()))
            self.inventory[product.id] = product

    def export_product(self, product_id):
        """
        Exports product object from warehouse if not already in it,
        appends export to log

        Params
        ---------------------------------------
        product_id : int
            Product to export

        Return
        ---------------------------------------
        product : Product
            Returns product object if exported, None if id does not exist in
            warehouse
        """
        if product_id in self.inventory.keys():
            tmp = self.inventory[product_id]
            if isinstance(tmp, Limited_Product):
                tmp.amount -= 1
                self.log.append('Product <{}> exported - {}'
                                .format(product_id, datetime.now()))
                if tmp.amount < 1:
                    self.remove_product(product_id)
                return tmp
            else:
                self.log.append('Product <{}> exported - {}'
                                .format(product_id, datetime.now()))
                return self.inventory[product_id]
        else:
            return None

    def size(self):
        """
        Size of warehouse

        Return
        ---------------------------------------
        size : int
            returns size of warehouse inventory
        """
        return len(self.inventory.values())

    def show_log(self):
        """
        Prints all log strings in the log. Each log occupies exactly one line.
        """
        for i in self.log:
            print(i)


#######################################################################
#                               HISTORY                               #
#######################################################################
class History:
    """
    This class is an implementation of the purchase history of a user

    Class Attributes
    ---------------------------------------
    history_counter : int
        count of history

    Object Attributes
    ---------------------------------------
    product : Product
        product purchased
    user : User
        user that purchased product
    id : int
        id of history object
    time : datetime
        time of purchase
    """
    history_counter = 0

    ##### Part 3 #####
    def __init__(self, product, user):
        """
        Constructor of History

        Params
        ---------------------------------------
        product : Product
            product that was purchased
        user : User
            user that purchased product
        """
        self.product = product
        self.user = user
        self.id = History.history_counter
        History.history_counter += 1
        self.time = datetime.now()

    def __str__(self):
        """
        String representation of History

        Return
        ---------------------------------------
        history : string
            Returns string representation of History

        ex: “<{history id}> {user id} bought {product} at {time}”
        """
        return '<{}> {} bought {} at {}'.format(
            self.id, self.user.id, self.product, self.time)

    def __repr__(self):
        """
        Object representation of History

        Return
        ---------------------------------------
        history : string
            Returns object representation of History

        ex: “HISTORY<{history id}> - {time}”
        """
        return 'HISTORY<{}> - {}'.format(self.id, self.time)


#######################################################################
#                                USER                                 #
#######################################################################
class User:
    """
    This class is an implementation of a user that interacts with stores

    Class Attributes
    ---------------------------------------
    user_counter : int
        count of users

    Object Attributes
    ---------------------------------------
    name : string
        name of user
    store : Store
        store that user interacts with
    balance : int
        total balance of user
    id : int
        id of user
    purchase_history : Stack
        history of purchases
    cart : Queue
        user cart containing potential purchases

    Methods
    ---------------------------------------
    set_name(new_name)
        Set the name attribute to the new_name.
    get_name()
        returns name attribute.
    set_balance(amount)
        Set the balance attribute to the amount.
    get_balance()
        returns the balance attribute.
    add_balance(amount)
        Increment the balance attribute by the specified amount.
    last_purchase()
        returns the last purchased history instance of this user.
        If the purchase history is empty, return None.
    view_history()
        Print the purchase history of this user.
    view_cart()
        Print the cart of this user.
    clear_cart()
        Remove all products in the cart.
    add_cart(product_id)
        A function that takes a product id and adds the corresponding
        product to the user’s shopping cart. Checks whether the store has this
        product, and if the store does have it, add this product object to
        the shopping cart. If the store cannot find the specified product,
        this function does nothing.
    checkout():
        A function that orders every item in the shopping cart from the store
        and returns a list of purchased products. Once an order fails,
        stop the rest of the orders (Any unordered product stays in the cart).
    undo_purchase():
        A function that undoes the last purchase of the user. If the user does
        not have any purchase history records, this function prints the
        message “USER: no purchase history” and terminate. Otherwise,
        the function will try to request the store to undo the last order.
        If the order is successfully undone, the last purchase from the user’s
        purchase history is removed else it left unchanged
    """
    user_counter = 0

    ##### Part 4.1 #####
    def __init__(self, name, store):
        """
        Constructor of User

        Params
        ---------------------------------------
        name : string
            name of user
        store : Store
            store that user interacts with
        """
        self.name = name
        self.store = store
        self.balance = 0
        self.id = User.user_counter
        User.user_counter += 1
        self.purchase_history = Stack()
        self.cart = Queue()
        self.store.add_user(self)

    def __str__(self):
        """
        String representation of User

        Return
        ---------------------------------------
        user : string
            Returns string representation of User

        ex: “standard user: {name} - {balance}$”
        """
        return 'standard user: {} - {}$'.format(self.name, self.balance)

    def __repr__(self):
        """
        Object representation of User

        Return
        ---------------------------------------
        user : string
            Returns object representation of User

        ex: “USER<{id}>”
        """
        return 'USER<{}>'.format(self.id)

    def set_name(self, new_name):
        """
        Set name of user

        Params
        ---------------------------------------
        new_name : string
            new name of user
        """
        self.name = new_name

    def get_name(self):
        """
        Get name of user

        Return
        ---------------------------------------
        name : string
            returns name of user
        """
        return self.name

    def set_balance(self, amount):
        """
        Set balance of user

        Params
        ---------------------------------------
        amount : int
            new balance of user
        """
        self.balance = amount

    def get_balance(self):
        """
        Get balance of user

        Return
        ---------------------------------------
        balance : int
            returns balance of user
        """
        return self.balance

    def add_balance(self, amount):
        """
        Add amount to user balance

        Params
        ---------------------------------------
        amount : int
            amount to add to balance
        """
        self.balance += amount

    def last_purchase(self):
        """
        Returns the last purchased history instance of this user.
        If the purchase history is empty, return None.

        Return
        ---------------------------------------
        hist : History
            returns latest history instance or None if Purchase_history
            is empty
        """
        return self.purchase_history.peek()

    def view_history(self):
        """
        Print the purchase history of this user.
        """
        print(self.purchase_history)

    def view_cart(self):
        """
        Print the cart of this user.
        """
        print(self.cart)

    def clear_cart(self):
        """
        Remove all products in the cart.
        """
        self.cart.clear()

    ##### Part 5.2 #####
    def add_cart(self, product_id):
        """
        A function that takes a product id and adds the corresponding
        product to the user’s shopping cart. Checks whether the store has this
        product, and if the store does have it, add this product object to
        the shopping cart. If the store cannot find the specified product,
        this function does nothing.

        Params
        ---------------------------------------
        product_id : int
            id of product to add to cart
        """
        if self.store.get_product(product_id) is not None:
            self.cart.enqueue(self.store.get_product(product_id))

    def checkout(self):
        """
        A function that orders every item in the shopping cart from the store
        and returns a list of purchased products. Once an order fails,
        stop the rest of the orders (Any unordered product stays in the cart).

        Return
        ---------------------------------------
        orders : list
            returns list of successful orders
        """
        orders = []
        for _ in range(self.cart.size()):
            itm = self.cart.peek()
            history = self.store.order(self.id, itm.id)
            if history:
                self.purchase_history.push(history)
                self.cart.dequeue()
                orders.append(itm)
            else:
                break
        return orders

    ##### Part 5.3 #####
    def undo_purchase(self):
        """
        A function that undoes the last purchase of the user. If the user does
        not have any purchase history records, this function prints the
        message “USER: no purchase history” and terminate. Otherwise,
        the function will try to request the store to undo the last order.
        If the order is successfully undone, the last purchase from the user’s
        purchase history is removed else it left unchanged
        """
        if self.purchase_history.is_empty():
            print('USER: no purchase history')
            return
        else:
            if self.store.undo_order(self.id,
                                     self.purchase_history.peek().product):
                self.purchase_history.pop()
            return


class Premium_User(User):
    """
    This class is a subclass of User that interacts with stores

    Methods
    ---------------------------------------
    buy_all(product_id)
        A function that supports batch ordering for limited products.
    undo_all()
        A function that iteratively cancels the last purchases until the
        user does not have any records in purchase history, or the last
        purchase is a limited product.
    """

    ##### Part 4.2 #####
    def __str__(self):
        """
        String representation of Premium_User

        Return
        ---------------------------------------
        user : string
            Returns string representation of Premium_User

        ex: "premium user: {name} - {balance}$"
        """
        return 'premium user: {} - {}$'.format(self.name, self.balance)

    ##### Part 5.4 #####
    def buy_all(self, product_id):
        """
        A function that supports batch ordering for limited products.
        This function takes the id (int) of the product to purchase and check
        if this product is a limited product. If it’s not, print the message
        “USER: not a limited product” and return an empty list. Otherwise,
        the function will repeatedly order this product until the product has
        no more offerings or the balance is exhausted. The function will add
        all the purchase history records to the user’s purchase history and
        return a list of purchased products

        Params
        ---------------------------------------
        product_id : int
            id of product to be purchased

        Return
        ---------------------------------------
        purchased : list
            Returns list of successfully purchased products
        """
        purchased = []
        if not isinstance(self.store.get_product(product_id), Limited_Product):
            print('USER: Not a limited product')
            return []
        else:
            history = self.store.order(self.id, product_id)
            while history is not False:
                self.purchase_history.push(history)
                purchased.append(history.product)
                history = self.store.order(self.id, product_id)
            return purchased

    def undo_all(self):
        """
        A function that iteratively cancels the last purchases until the
        user does not have any records in purchase history, or the last
        purchase is a limited product.
        """
        while not isinstance(self.purchase_history.peek().product,
                             Limited_Product):
            self.undo_purchase()


#######################################################################
#                               STORE                                 #
#######################################################################
class Store:
    """
    This class is an implementation of a store that interacts with users

    Object Attributes
    ---------------------------------------
    users : dict
        dictionary containing users in this format, user_id: User
    warehouse : Warehouse
        warehouse that supplies the store

    Methods
    ---------------------------------------
    get_product(product_id)
        Lookup a product instance with the provided product_id (int) from
        the warehouse and return this product.
    view_products(sort=False)
        Print all products in the inventory in a human readable format
    add_user(user)
        A function that takes a user instance and records this instance
        to the users dictionary. If the user is already present in the users
        dictionary, print the message “STORE: User already exists” and return
        False. Otherwise, add this new user to the dictionary and return True.
    order(user_id, product_id)
        A function that takes the ids (int) of the user who makes this order
        and the product this user orders, and handles the order.
    undo_order(user_id, product)
        A function that takes the id (int) of the user who requested order
        cancellation and the product instance (Product) in this order, and
        processes the refund process.
    """

    ##### Part 4.3 #####
    def __init__(self, warehouse):
        """
        Constructor of Store

        Params
        ---------------------------------------
        warehouse : Warehouse
            warehouse that supplies this store
        """
        self.users = {}
        self.warehouse = warehouse

    def __str__(self):
        """
        String representation of Store

        Return
        ---------------------------------------
        store : string
            Returns string representation of Store

        ex: "STORE: store with {number of users} users and
            {size of warehouse} products"
        """
        return 'STORE: store with {} users and {} products'.format(
            len(self.users.keys()), self.warehouse.size())

    def get_product(self, product_id):
        """
        Get product from store

        Return
        ---------------------------------------
        product_id : int
            returns product object
        """
        return self.warehouse.get_product(product_id)

    def view_products(self, sort=False):
        """
        Print all products in the inventory in a human readable format

        Params
        ---------------------------------------
        sort : boolean, default=False
            determines if products are printed out sorted
        """
        header = '============================\n<ID> Product - Price\n'
        footer = '\n============================'
        if sort:
            new = list(map(lambda x: str(x),
                           sorted(
                               [i for i in self.warehouse.inventory.values()],
                               key=lambda x: x.price)))
            print(header + '\n'.join(new) + footer)
        else:
            new = [str(i) for i in self.warehouse.inventory.values()]
            print(header + '\n'.join(new) + footer)

    ##### Part 5.1 #####
    def add_user(self, user):
        """
        A function that takes a user instance and records this instance to the
        users dictionary. If the user is already present in the users
        dictionary, print the message “STORE: User already exists” and return
        False. Otherwise, add this new user to the dictionary and return True.

        Params
        ---------------------------------------
        user : User
            user to be added

        Return
        ---------------------------------------
        user : boolean
            returns True if user is added, False if they already exist in users
        """
        if user in self.users.values():
            print('STORE: User already exists')
            return False
        else:
            self.users[user.id] = user
            return True

    ##### Part 5.2 #####
    def order(self, user_id, product_id):
        """
        A function that takes the ids (int) of the user who makes this order
        and the product this user orders, and handles the order. Deducts the
        payment from the user's balance, exports the ordered product from
        the warehouse, prints the message “STORE: {user name} ordered
        {product name}. {user name} has {user balance}$ left.”, and
        returns a new payment history (History instance).

        Params
        ---------------------------------------
        user_id : int
            id of user
        product_id : int
            id of product

        Return
        ---------------------------------------
        order : History
            returns History object if order is successful else returns
            False
        """
        if self.get_product(product_id) is None:
            print('STORE: Product not found')
            return False
        if (isinstance(self.warehouse.inventory[product_id],
                       Special_Product)) and (
                not isinstance(self.users[user_id],
                               Premium_User)):
            print('STORE: Special product is limited to premium user')
            return False
        if isinstance(self.users[user_id], Premium_User):
            price = self.warehouse.inventory[product_id].price
            if self.users[user_id].balance < price:
                print('STORE: Not enough money QQ')
                return False
            else:
                prod = self.get_product(product_id)
                self.users[user_id].balance -= price
                self.warehouse.export_product(product_id)
                print(
                    'STORE: {} ordered {}. {} has {}$ left.'.format(
                        self.users[user_id].name,
                        prod.name,
                        self.users[user_id].name, self.users[user_id].balance))
                return History(prod,
                               self.users[user_id])
        else:
            price = int(self.warehouse.inventory[product_id].price * 1.1)
            if self.users[user_id].balance < price:
                print('STORE: Not enough money QQ')
                return False
            else:
                prod = self.get_product(product_id)
                self.users[user_id].balance -= price
                self.warehouse.export_product(product_id)
                print(
                    'STORE: {} ordered {}. {} has {}$ left.'.format(
                        self.users[user_id].name,
                        prod.name,
                        self.users[user_id].name, self.users[user_id].balance))
                return History(prod,
                               self.users[user_id])

    ##### Part 5.3 #####
    def undo_order(self, user_id, product):
        """
        A function that undoes the last purchase of the user.

        Params
        ---------------------------------------
        user_id : int
            id of user
        product : Product
            product object involved in order

        Return
        ---------------------------------------
        undo : boolean
            returns True if undo was successful, False otherwise
        """
        if isinstance(product, Limited_Product):
            print('STORE: Can\'t refund Limited_Product')
            return False
        else:
            self.users[user_id].balance += product.price
            print(
                'STORE: {} refunded {}. {} has {}$ left.'.format(
                    self.users[user_id].name, product.name,
                    self.users[user_id].name, self.users[user_id].balance))
            return True

    ##### Part 6 #####
    def so_rich(self, money):
        """
        A function that maximizes the amount of money spent and returns the
        money left.

        Params
        ---------------------------------------
        money : int
            total amount of money to spend

        Return
        ---------------------------------------
        min : int
            returns the minimum amount of money left after spending as
            much of it as possible
        """
        # suppose you haven't seen any product yet
        # the only possible amount of money left is "money"
        # this is a set to record the possible money left
        left = set([money])

        # get products
        lst = [i for i in self.warehouse.inventory.values()]

        for product in lst:
            # a temporary set to save the updates of "left"
            # you don't want to modify the set you're iterating through
            tmp_left = set()

            for m in left:
                # update tmp_left
                if type(product) != Limited_Product:
                    new_left = m
                    while new_left >= 0:
                        tmp_left.add(new_left)
                        new_left = new_left - product.price
                else:
                    # handle limited product
                    new_left = m
                    amt_left = product.amount
                    while new_left >= 0 and amt_left >= 0:
                        tmp_left.add(new_left)
                        new_left = new_left - product.price
                        amt_left -= 1
            left = tmp_left

        return min(left)

    def so_rich_recursive(self, money):
        """
        A recursive function that maximizes the amount of money spent and
        returns the money left.

        Params
        ---------------------------------------
        money : int
            total amount of money to spend

        Return
        ---------------------------------------
        min : int
            returns the minimum amount of money left after spending as
            much of it as possible
        """
        # get products
        lst = [i for i in self.warehouse.inventory.values()]

        def helper(lst, money):
            # base case
            if len(lst) < 1:
                return money

            cur_min = money
            product = lst[0]
            if type(product) != Limited_Product:
                tmp = money
                while tmp >= 0:
                    rcrsn = helper(lst[1:], tmp)
                    tmp -= product.price
                    if rcrsn < cur_min:
                        cur_min = rcrsn
            else:
                tmp = money
                amt = product.amount
                while tmp >= 0 and amt >= 0:
                    rcrsn = helper(lst[1:], tmp)
                    tmp -= product.price
                    amt -= 1
                    if rcrsn < cur_min:
                        cur_min = rcrsn
            return cur_min

        return helper(lst, money)
