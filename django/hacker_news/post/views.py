from django.db.models import F
from django.http import HttpResponseBadRequest
from django.shortcuts import render, get_object_or_404, redirect

from post.forms import PostForm
from post.models import Post


# Create your views here.

def posts_view(request):
    if request.method == "GET":
        posts = Post.objects.all()
        context = {"posts": posts, "form": PostForm,}
        return render(
            request,
            "post_list.html",
            context,
        )
    elif request.method == "POST":

        form = PostForm(request.POST)
        if form.is_valid():
            title = form.cleaned_data["title"]
            body = form.cleaned_data["body"]
            author_name = form.cleaned_data["author_name"]

            post = Post.objects.create(
                title=title,
                body=body,
                author_name=author_name,
            )

            return render(
                request,
                "post_detail.html",
                {"post": post, },
            )
        return redirect("posts")


def post_view(request, post_id):
    post = get_object_or_404(Post, id=post_id)
    context = {"post": post, }
    return render(
        request,
        "post_detail.html",
        context,
    )

def post_like_view(request, post_id):
    post = get_object_or_404(Post, id=post_id)
    post.points = F("points") + 1
    post.save()
    post.refresh_from_db()
    return render(
        request,
        "post_detail.html",
        {"post": post, },
    )
