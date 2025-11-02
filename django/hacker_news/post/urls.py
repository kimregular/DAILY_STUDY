from django.urls import path

from post import views

urlpatterns = [
    path("", views.posts_view, name="posts",),
    path("<int:post_id>", views.post_view, name="post",),
    path("<int:post_id>/like", views.post_like_view, name="post_like",),
]