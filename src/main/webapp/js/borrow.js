const select = document.getElementById("bookSelect");
const preview = document.getElementById("bookPreview");

const pTitle = document.getElementById("pTitle");
const pAuthor = document.getElementById("pAuthor");

select.addEventListener("change", () => {
    const opt = select.options[select.selectedIndex];

    if (!opt.value) {
        preview.classList.add("hidden");
        return;
    }

    pTitle.textContent = opt.dataset.title;
    pAuthor.textContent = opt.dataset.author;

    preview.classList.remove("hidden");
});
