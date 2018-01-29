var BasketComponent = function(data) {
   var template = [
       '<div class="basket__info float-right">',
           '<span class="count">__items_count__ </span>',
           ' | ',
           '<span class="subtotal"> __total__ __currency__</span>',
           '<a class="continue-to-checkout" href="#"><i class="fas fa-arrow-right"></i></a>',
       '</div>',
   ];

   return {
        render: function() {
            var tmp = template.join("");
            tmp = tmp.replace("__items_count__", data.itemsCount);
            tmp = tmp.replace("__total__", data.total);
            tmp = tmp.replace("__currency__", data.currency);

            return tmp;
        }
   }
}

var enableCheckout = function(checkoutElement) {
    checkoutElement.addEventListener("click", function(){
        checkout.initialize();
    });
}

var loadBasketInformation = function(basketContainer) {
    axios.get('/offer')
        .then(function(response) {
            basketContainer.innerHTML = (new BasketComponent(response.data)).render();

            var checkout = basketContainer.getElementsByClassName("continue-to-checkout")[0];

            enableCheckout(checkout);
        })
    ;


}

var basketContainer = document.getElementsByClassName("basket")[0];

document.addEventListener("DOMContentLoaded", function() {
    basketContainer.innerHTML = (new BasketComponent({
        itemsCount: 0,
        total: 0,
        currency: "PLN"
    })).render();
    loadBasketInformation(basketContainer);
});

document.addEventListener('item_placed_in_basket', function(e) {
    loadBasketInformation(basketContainer);
});
