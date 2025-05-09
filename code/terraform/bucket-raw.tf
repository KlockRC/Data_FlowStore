resource "aws_s3_bucket" "raw" {
  bucket = "rawlayer24042025"
  force_destroy = "true"

  tags = {
    Name        = "Raw Layer"
    Environment = "Test"
  }
}

resource "aws_s3_bucket_ownership_controls" "raw" {
  bucket = aws_s3_bucket.raw.id
  rule {
    object_ownership = "BucketOwnerPreferred"
  }
}
resource "aws_s3_bucket_acl" "raw" {
  depends_on = [aws_s3_bucket_ownership_controls.raw]

  bucket = aws_s3_bucket.raw.id
  acl    = "private"
}
resource "aws_s3_bucket_public_access_block" "raw" {
  bucket = aws_s3_bucket.raw.id

  block_public_acls       = true
  block_public_policy     = true
  ignore_public_acls      = true
  restrict_public_buckets = true
}
resource "aws_s3_bucket_versioning" "versioning_raw" {
  bucket = aws_s3_bucket.raw.id
  versioning_configuration {
    status = "Disabled"
  }
}
data "aws_iam_policy_document" "raw_write" {
  statement {
    actions = [
      "s3:PutObject",
      "s3:ListBucket",
      "s3:GetObject",
      "s3:AbortMultipartUpload"
    ]
    resources = ["${aws_s3_bucket.raw.arn}/*"]
  }
}

