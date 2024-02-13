<template>
  <div id="app">
    <nav class="navbar navbar-expand navbar-dark bg-dark">
      <router-link to="/" class="navbar-brand">WebDirectoryManager</router-link>
      <div class="navbar-nav mr-auto">
        <li class="nav-item">
          <router-link to="/professions" class="nav-link">Professions</router-link>
        </li>
        <li class="nav-item">
          <router-link to="/departments" class="nav-link">Departments</router-link>
        </li>
        <li class="nav-item">
          <router-link to="/employees" class="nav-link">Employees</router-link>
        </li>

      </div>
    </nav>

    <div class="container mt-3">
      <router-view />
    </div>
  </div>
</template>

<script>
export default {
  name: "app"
};
</script>