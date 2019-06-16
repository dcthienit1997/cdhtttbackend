$.trumbowyg.svgPath = '../vendor/trumbowyg/dist/ui/icons.svg';
$("#trumbowyg").trumbowyg({
    btnsDef: {
        // create a new dropdown
        image: {
            dropdown: ['insertImage', 'base64'],
            ico: 'insertImage'
        }
    },
    // redefine the button pane
    btns:
        [
            ['viewHTML'],
            ['fontsize'],
            ['strong', 'em', 'del'],
            ['justifyLeft', 'justifyCenter'],
            ['image'], // our fresh created dropdown
            ['link'],
            ['unorderedList', 'orderedList'],
            ['horizontalRule'],
            ['fullscreen']
        ]
})
;
let form = document.forms[0];
Array.from(form.getElementsByTagName("button")).filter(e => e.type === 'submit').forEach(btn =>
    btn.addEventListener('click', e => {
        e.preventDefault();
        form.setAttribute('action', '/' + e.target.name);
        console.log(form.getAttribute('action'));
        form.submit();
    })
);
