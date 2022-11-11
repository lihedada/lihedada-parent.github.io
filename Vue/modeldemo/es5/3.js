// 删除
removeDataById(id) ;
    this.$confirm('此操作将永久删除该角色,是否继续?','提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // 调用方法删除
        api.removeId(id)
          .then(response => {
            // 提示
            this.$message({
              type: 'success',
              message: '删除成功!'
            })
            // 刷新
            this.fetchData()
          });     
        })
    