package com.johnmelodyme.foodie.Model
/**
 * Copyright © 2021 by John Melody Me
 * <p>
 * All rights reserved. No part of this software may be reproduced,
 * distributed, or transmitted in any form or by any means, including
 * photocopying, recording, or other electronic or mechanical methods,
 * without the prior written permission of the developer, except in the
 * case of brief quotations embodied in critical reviews and certain other
 * noncommercial uses permitted by copyright law. For permission requests,
 * write to the code-owner, addressed “Attention: Permissions Coordinator,”
 * at the address below.
 * <p>
 * https://johnmelodyme.github.io/
 */
class RecipeModelQR
{
    lateinit var recipeName: String
    lateinit var imageUrl: String
    lateinit var ingredient: String
    lateinit var description: String
    lateinit var data: String

    constructor(
        recipeName: String,
        imageUrl: String,
        ingredient: String,
        description: String,
        data: String
    )
    {
        this.recipeName = recipeName
        this.imageUrl = imageUrl
        this.ingredient = ingredient
        this.description = description
        this.data = data
    }

    constructor()
}